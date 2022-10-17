package com.bd.post.service;

import io.envoyproxy.pgv.ExplicitValidatorIndex;
import io.envoyproxy.pgv.ReflectiveValidatorIndex;
import io.envoyproxy.pgv.ValidationException;
import io.envoyproxy.pgv.ValidatorIndex;
import com.bd.post.repository.UserRepository;
import com.bd.post.util.CopyUtils;
import com.bd.post.model.User;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.badata.protobuf.converter.Configuration;
import net.badata.protobuf.converter.Converter;
import net.badata.protobuf.converter.FieldsIgnore;
import net.devh.boot.grpc.server.service.GrpcService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import sample.UserProto;
import sample.UserProtoValidator;
import sample.UserServiceGrpc;

/**
 * @author <a href="mailto:hilin2333@gmail.com">created by silencecorner 2019/7/10 3:25 PM</a>
 */
@Slf4j
@AllArgsConstructor
@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void addUser(UserProto.AddUserRequest request, StreamObserver<UserProto.User> responseObserver) {
        User user = userRepository.save(new User(request.getUsername(), request.getEmail(), request.getPassword(), request.getRole()));
        responseObserver.onNext(modelToRpc(user));
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void updateUser(UserProto.UpdateUserRequest request, StreamObserver<UserProto.User> responseObserver){
        check(request);
        // field_mask
        Configuration configuration = Configuration.builder().addIgnoredFields(new FieldsIgnore().add(User.class, "createdAt")).build();
        User user = Converter.create(configuration).toDomain(User.class, request);
        User newUser = userRepository.getOne(user.getId());

        CopyUtils.copyProperties(user, newUser, true);
        responseObserver.onNext(modelToRpc(userRepository.save(newUser)));
        responseObserver.onCompleted();
    }

    @Override
    public void getUser(UserProto.GetUserRequest request, StreamObserver<UserProto.User> responseObserver) {
        //add new db call to repository class
        Optional<User> user = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        if (user.isPresent()){
            responseObserver.onNext(UserProto.User.newBuilder()
                    .setId(user.get().getId())
                    .setUsername(user.get().getUsername())
                    .setEmail(user.get().getEmail())
                    .setRole(user.get().getRole())
                    .setPassword(user.get().getPassword())
                    .build());
        }else{
            responseObserver.onNext(UserProto.User.getDefaultInstance());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void listUsers(UserProto.ListUsersRequest request, StreamObserver<UserProto.Users> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<User> page = request.getRole().equals(null)
                ? userRepository.findAll(pageable)
                : userRepository.findByRole(request.getRole(), pageable);
        responseObserver.onNext(UserProto.Users.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount((int) page.getTotalElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();
    }

    private UserProto.User modelToRpc(User user) {

        return Converter.create().toProtobuf(UserProto.User.class, user);
    }

    /**
     * 从新增条件copy验证条件
     * @see PostProtoValidator.AddPostRequestValidator#assertValid
     * @param request 请求参数
     */
    private void check(UserProto.UpdateUserRequest request){
        boolean checkFlag = false;
        if (request.getId() == 0){
            throw Status.INVALID_ARGUMENT.asRuntimeException();
        }
        try {
            if (!request.getUsername().equals( "")){
                io.envoyproxy.pgv.StringValidation.minLength(".sample.UpdateUserRequest.username", request.getUsername(), 1);
                io.envoyproxy.pgv.StringValidation.maxLength(".sample.UpdateUserRequest.username", request.getUsername(), 100);
            }
            if (!request.getEmail().equals( "")){
                io.envoyproxy.pgv.StringValidation.minLength(".sample.UpdateUserRequest.email", request.getEmail(), 1);
                io.envoyproxy.pgv.StringValidation.maxLength(".sample.UpdateUserRequest.email", request.getEmail(), 20000);
            }
            if (!request.getRole().equals( "")){
                io.envoyproxy.pgv.StringValidation.minLength(".sample.UpdateUserRequest.role", request.getRole(), 1);
                io.envoyproxy.pgv.StringValidation.maxLength(".sample.UpdateUserRequest.role", request.getRole(), 20);
            }
            if (!request.getPassword().equals( "")){
                io.envoyproxy.pgv.StringValidation.minLength(".sample.UpdateUserRequest.password", request.getPassword(), 1);
                io.envoyproxy.pgv.StringValidation.maxLength(".sample.UpdateUserRequest.password", request.getPassword(), 20000);
            }
        }catch (ValidationException e){
            throw Status.INVALID_ARGUMENT.withCause(e).withDescription(e.getMessage()).asRuntimeException();
        }
    }
}
