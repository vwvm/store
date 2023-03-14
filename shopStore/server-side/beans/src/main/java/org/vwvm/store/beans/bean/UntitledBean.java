package org.vwvm.store.beans.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <h3>store</h3>
 * <p>自定义untitled实体类</p>
 *
 * @author : BlackBox
 * @date : 2023-03-08 11:40
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UntitledBean {
    String string;

    List<String> stringList;
}
