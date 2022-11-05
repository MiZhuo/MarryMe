package site.mizhuo.marryfriends;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.mizhuo.marryfriends.entity.FriendInfo;
import site.mizhuo.marryfriends.mapper.FriendInfoMapper;

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
