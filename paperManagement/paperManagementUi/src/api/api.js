import * as __carousel from "./carousel";
import * as __home from "./home";
import * as __project from "./project.js";
import * as __user from "./user";
import * as __captcha from "./captcha";
import * as __download from "./download";
import * as __teacher from "./teacher.js";
import * as __department from "./department.js";
import * as __student from "./student.js";


export default {
    __carousel,
    __home,
    __project,
    __user,
    __captcha,
    __download,
    __teacher
}

export const downloadApi= __download;
export const projectApi = __project;
export const studentApi = __student;
export const departmentApi = __department;
export const teacherApi = __teacher;
export const userApi = __user;
