package application.hibernate.services.composite;

import java.util.List;

import application.hibernate.entities.composite.UserCollection;

public interface UserCollectionService {
    UserCollection saveUserCollection(UserCollection userCollection);

    List<UserCollection> getAllUserCollections();
}
