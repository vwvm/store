package org.vwvm.store.apiserver.repository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.annotation.Resource;
import org.vwvm.store.apiserver.ApiServerApplication;
import org.vwvm.store.apiserver.entity.Address;
import org.vwvm.store.apiserver.entity.UserDetail;
import org.vwvm.store.apiserver.entity.UserInfo;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = ApiServerApplication.class)
@ExtendWith(SpringExtension.class)
public class UserDetailRepositoryTests {

	@Resource
	private AddressRepository addressRepository;
	@Resource
	private UserDetailRepository userDetailRepository;

	@Test
	public void testSaveAddress() {
		Address address=new Address();
		address.setUserId(1L);
		address.setCity("北京");
		address.setProvince("北京");
		address.setStreet("分钟寺");
		addressRepository.save(address);
	}

	@Test
	public void testSaveUserDetail() {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);
		UserDetail userDetail=new UserDetail();
		userDetail.setUserId(3L);
		userDetail.setHobby("吃鸡游戏");
		userDetail.setAge(28);
		userDetail.setIntroduction("一个爱玩的人");
		userDetailRepository.save(userDetail);
	}

	@Test
	public void testUserInfo()  {
		List<UserInfo> userInfos=userDetailRepository.findUserInfo("钓鱼");
		for (UserInfo userInfo:userInfos){
			System.out.println("userInfo: "+userInfo.getUserName()+"-"+userInfo.getEmail()+"-"+userInfo.getHobby()+"-"+userInfo.getIntroduction());
		}
	}
}
