package application.hibernate.services.composite;

import java.util.List;

import application.hibernate.entities.composite.UserCollection;
import application.hibernate.repos.composite.UserCollectionRepository;

public class UserCollectionServiceImpl implements UserCollectionService {
    UserCollectionRepository userCollectionRepository = new UserCollectionRepository();

    @Override
    public UserCollection saveUserCollection(UserCollection userCollection) {
        return userCollectionRepository.save(userCollection);
    }

    @Override
    public List<UserCollection> getAllUserCollections() {
        return userCollectionRepository.findAll();
    }

}
