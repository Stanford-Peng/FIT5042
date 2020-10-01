package repository;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import entity.NormalUser;
import entity.User;

@Remote
public interface UserRepository {
	
	public void addUser(NormalUser user) throws Exception;
	public List<NormalUser> getAllUsers() throws Exception;
	public void updateUser(NormalUser user) throws Exception;
	public void deleteUser(String account) throws Exception;
	
	public NormalUser searchUser(String account) throws Exception;
	

}
