package org.vwvm.store.apiserver.repository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.annotation.Resource;
import org.vwvm.store.apiserver.ApiServerApplication;
import org.vwvm.store.apiserver.entity.UserDetail;
import org.vwvm.store.apiserver.param.UserDetailParam;
import org.vwvm.store.apiserver.service.UserDetailService;

@SpringBootTest(classes = ApiServerApplication.class)
@ExtendWith(SpringExtension.class)
public class JpaSpecificationTests {

	@Resource
	private UserDetailService userDetailService;

	@Test
	public void testFindByCondition()  {
		int page=0,size=10;
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
		UserDetailParam param=new UserDetailParam();
		param.setIntroduction("程序员");
		param.setMinAge(10);
		param.setMaxAge(30);
		Page<UserDetail> page1=userDetailService.findByCondition(param,pageable);
		for (UserDetail userDetail:page1){
			System.out.println("userDetail: "+userDetail.toString());
		}
	}

}
