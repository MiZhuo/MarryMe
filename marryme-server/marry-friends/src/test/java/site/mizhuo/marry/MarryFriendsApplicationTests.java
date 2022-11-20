package site.mizhuo.marry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.mizhuo.marry.friends.domain.FriendInfo;
import site.mizhuo.marry.friends.mapper.FriendInfoMapper;

import java.util.List;

@SpringBootTest
class MarryFriendsApplicationTests {


    @Autowired
    private FriendInfoMapper mapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<FriendInfo> friendInfos = mapper.selectList(null);
        friendInfos.forEach(System.out::println);
    }

}
