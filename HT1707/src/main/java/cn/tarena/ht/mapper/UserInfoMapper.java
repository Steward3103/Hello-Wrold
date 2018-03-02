package cn.tarena.ht.mapper;

import java.util.List;

import cn.tarena.ht.pojo.UserInfo;

public interface UserInfoMapper {

	public List<UserInfo> findUserInfoList();

	public void saveUserInfo(UserInfo info);

	public void updateUser(UserInfo info);

	public void deleteUserInfos(String[] userIds);

}
