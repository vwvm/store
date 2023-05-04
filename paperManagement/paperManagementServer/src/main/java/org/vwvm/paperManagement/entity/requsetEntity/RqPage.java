package org.vwvm.paperManagement.entity.requsetEntity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(name = "请求页面参数", description = "根据角色喝id请求")
public class RqPage {
        @Schema(name = "当前页")
        Integer pageNum;
        @Schema(name = "页面大小")
        Integer pageSize;
        @Schema(name = "角色", description = "0 管理员,1 教师, 2 学生")
        Integer role;
        @Schema(name = "id", description = "相关角色的id")
        Integer id;
        @Schema(name = "查找字符串")
        String findStr;
        @Schema(name = "开始时间", description = "格式 YYYY-MM-DD HH:mm:ss")
        String startTime;
        @Schema(name = "结束时间", description = "格式 YYYY-MM-DD HH:mm:ss")
        String endTime;

        @Schema(name = "编号1", description = "备用编号1")
        Integer index1;

        @Schema(name = "编号2", description = "备用,编号2")
        Integer index2;

        @Schema(name = "信息1", description = "备用,信息1")
        String msg1;

        @Schema(name = "信息2", description = "备用,信息2")
        String msg2;

        public Integer getPageNum() {
                return Objects.requireNonNullElse(pageNum, 1);
        }

        public void setPageNum(Integer pageNum) {
                this.pageNum = pageNum;
        }

        public Integer getPageSize() {
                return Objects.requireNonNullElse(pageSize, 5);
        }

        public void setPageSize(Integer pageSize) {
                this.pageSize = pageSize;
        }

        public Integer getRole() {
                return role;
        }

        public void setRole(Integer role) {
                this.role = role;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getFindStr() {return Objects.requireNonNullElse(findStr, "");
        }

        public void setFindStr(String findStr) {
                this.findStr = findStr;
        }

        public String getStartTime() {
                return Objects.requireNonNullElse(startTime, "");
        }

        public void setStartTime(String startTime) {
                this.startTime = startTime;
        }

        public String getEndTime() {
                return Objects.requireNonNullElse(endTime, "");
        }

        public void setEndTime(String endTime) {
                this.endTime = endTime;
        }

        public Integer getIndex1() {
                return index1;
        }

        public void setIndex1(Integer index1) {
                this.index1 = index1;
        }

        public Integer getIndex2() {
                return index2;
        }

        public void setIndex2(Integer index2) {
                this.index2 = index2;
        }

        public String getMsg1() {
                return msg1;
        }

        public void setMsg1(String msg1) {
                this.msg1 = msg1;
        }

        public String getMsg2() {
                return msg2;
        }

        public void setMsg2(String msg2) {
                this.msg2 = msg2;
        }
}
