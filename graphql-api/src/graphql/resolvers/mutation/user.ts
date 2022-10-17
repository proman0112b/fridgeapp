import userClient from '../../services/userClient';


const addUser: Function = (root: any, params: any) => {
    return new Promise((resolve: any, reject: any) => {
        userClient.addUser(params.request, function (err: any, response: any) {
            if (err) {
                return reject(err.details);
            }
            resolve(response);
        });
    });
};

const updateUser: Function = (root: any, params: any) => {
    return new Promise((resolve: any, reject: any) => {
        userClient.updateUser(params.request, function (err: any, response: any) {
            if (err) {
                return reject(err.details);
            }
            resolve(response);
        });
    });
};


export { addUser, updateUser };
