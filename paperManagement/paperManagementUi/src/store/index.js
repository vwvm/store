import {defineStore} from 'pinia'


//
export const useUser = defineStore('user', {
    // 定义状态
    state: () => ({
        user: {
            id: '',
            username: '',
            token: '',
            isTrue: "",
            userTelephone: '',
            role: [],
        }

    }),
    getters: {
        getUsername() {
            return this.user.username
        },
        getId() {
            return this.user.id
        },
        getUserTelephone() {
            return this.user.userTelephone
        },
        getToken() {
            return this.user.token
        },

    },
    // 操作状态的方法
    actions: {
        // 设置索引
        setUser(value) {
            this.user = value;
        },
        // 清空
        empty() {
            this.user = {
                id: '',
                username: '',
                token: '',
                isTrue: "",
                userTelephone: '',
                role: [],
            }
        }
    }
})

export const useTeacher = defineStore('teacher', {
    // 定义状态
    state: () => ({
        teacher: {
            id: '',
            userId: '',
            departmentId: '',
            teacherName: '',
            teacherDescribe: "",
            teacherProjectNum: '',
            teacherRank: '',
        }

    }),
    getters: {
        getUserId() {
            return this.teacher.userId
        },
        getId() {
            return this.teacher.id
        },
        getDepartmentId() {
            return this.teacher.departmentId
        },
        getTeacherName() {
            return this.teacher.teacherName
        },
        getTeacherDescribe() {
            return this.teacher.id
        },
        getDepartmentProjectNum() {
            return this.teacher.departmentId
        },
        getTeacherRank() {
            return this.teacher.teacherName
        },

    },
    // 操作状态的方法
    actions: {
        // 设置索引
        setTeacher(value) {
            this.teacher = value;
        },
        // 清空
        empty() {
            this.teacher = {
                id: '',
                userId: '',
                departmentId: '',
                teacherName: '',
                teacherDescribe: "",
                teacherProjectNum: '',
                teacherRank: '',
            }
        }
    }
})

//
export const useStudent = defineStore('Student', {
    // 定义状态
    state: () => ({
        student: {
            id: 0,
            userId: 0,
            majorId: 0,
            studentState: 0,
            studentName: "",
            studentDescribe: '学生描述',
            studentClass: "",
            studentScore: 0,
            majorName: "",
            teacherId:'',
        }

    }),
    getters: {
        getStudentName() {
            return this.student.studentName
        },
        getId() {
            return this.student.id
        },
        getMajorName() {
            return this.student.majorName
        },
        getStudentDescribe() {
            return this.student.studentDescribe
        },
        getStudentClass() {
            return this.student.studentClass
        },
        getStudentScore() {
            return this.student.studentScore
        },
        getStudentState() {
            return this.student.studentState
        },
        getUserId() {
            return this.student.userId
        },
        getTeacherName(){
            return this.student.teacherId
        }
    },
    // 操作状态的方法
    actions: {
        // 设置索引
        setStudent(value) {
            this.student = value;
        },
        // 清空
        empty() {
            this.student = {
                id: 0,
                userId: 0,
                majorId: 0,
                studentState: 0,
                studentName: "",
                studentDescribe: '学生描述',
                studentClass: "",
                studentScore: 0,
                teacherId:'',
            }
        }
    }
})
