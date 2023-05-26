package com.krish.writeopia.business_layer;

import com.krish.writeopia.models.Connections;
import com.krish.writeopia.models.MyUsers;
import com.krish.writeopia.repository.ConnectionsRepository;
import com.krish.writeopia.repository.MyUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyUsersService {
    @Autowired
    private MyUsersRepository myUsersRepository;
    @Autowired
    private ConnectionsRepository connectionsRepository;

    public Optional<MyUsers> showUserByUserNameService(String userName){
        return myUsersRepository.findMyUsersByUserName(userName);
    }

    public MyUsers showUserByIdService(Long id){
        return myUsersRepository.findMyUsersById(id);
    }

    public void addUserService(MyUsers user){
        myUsersRepository.save(user);
    }

    //User Connections Services
    public List<Connections> alltypeConnectionsUserService(String userName){
        return connectionsRepository.findConnectionsByReceiver_UserName(userName);
    }

    public List<Connections> showUserConnectionsListService(String userName){
        return connectionsRepository.findConnectionsByReceiver_UserNameAndAcceptedTrue(userName);
    }

    public List<Connections> showUserPendingListService(String userName){
        return connectionsRepository.findConnectionsByReceiver_UserNameAndAcceptedFalseAndRequestedTrue(userName);
    }

    public List<Connections> showUserFollowersListService(String userName){
        return connectionsRepository.findConnectionsByReceiver_UserNameAndFollowingIsTrue(userName);
    }

    public void addModConnectionService(Connections connection){
        connectionsRepository.save(connection);
    }

    public void removeConnectionService(Long id){
        connectionsRepository.deleteById(id);
    }

    public Connections checkConnectionStatus(String receiver_userName, String sender_userName){
        return connectionsRepository.findConnectionsByReceiver_UserNameAndSender_UserName(receiver_userName, sender_userName);
    }

}
