import com.zero.product.ProductServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yezhaoxing
 * @since 2018/09/28
 */
@SpringBootTest(classes = ProductServerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EncryptTest {


    @Test
    public void encrypt() {
        System.out.println("hello");
    }
}
