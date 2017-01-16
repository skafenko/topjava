package ru.javawebinar.topjava.service.user;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by Mykhailo on 16.01.2017.
 */

@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.JDBC})
public class UserServiceJdbcTest extends UserServiceAbstTest {
}
