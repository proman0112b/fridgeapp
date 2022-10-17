import { addPost, updatePost } from './mutation/post';
import { addUser, updateUser } from './mutation/user';
import { listPosts, posts } from './query/post';
import { author, getAuthor } from './query/author';
import { getUser, listUsers } from './query/user';
import { addAuthor } from './mutation/author';
import {DateTimeScalar} from '../scalar'

const resolvers: any  = {
  DateTime: DateTimeScalar,
  Mutation: {
    addPost,
    updatePost,
    addAuthor,
    addUser,
    updateUser
  },
  Query: {
    getAuthor,
    listPosts,
    getUser,
    listUsers
  },
  Author: {
    posts
  },
  Post: {
    author
  },
};

export default resolvers;
