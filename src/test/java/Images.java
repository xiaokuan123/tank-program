import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
public class Images {
    @Test
    public void getmage(){
        String path ="E:/bestpayWorkspace/car-pargrome/springboot-tank/src/main/resources/images/tank01.png";
        try {
            BufferedImage read = ImageIO.read(new File(path));
            BufferedImage tankU = ImageIO.read(Images.class.getClassLoader().getResourceAsStream("explain/E3.png"));
            System.out.println(read);
            System.out.println(tankU);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(1);
    }
}
