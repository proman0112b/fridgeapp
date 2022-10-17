import userClient  from '../../services/userClient';
import postClient from "../../services/postClient";

// const user: Function = (root: any, params: any) => {
//     return new Promise((resolve: any, reject: any) => {
//         userClient.getUser({id : root.id}, function(err: any, response: any) {
//             if (err) {
//                 return reject(err);
//             }
//             resolve(response);
//         });
//     });
// };

const getUser: Function = (root: any, params: any) => {
    return new Promise((resolve: any, reject: any) => {
        userClient.getUser(params.request, function(err: any, response: any) {
            if (err) {
                return reject(err);
            }
            resolve(response);
        });
    });
};

const listUsers: Function = (root: any, params: any) => {
    return new Promise((resolve: any, reject: any) => {
        userClient.listUsers(params.request, function(err: any, response: any) {
            if (err) {
                return reject(err);
            }
            resolve(response);
        });
    });
};

export {getUser, listUsers}