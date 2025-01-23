// This makes buffered image serializable

package remote;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;

public class SerializableBufferedImage implements Serializable {
    private BufferedImage image;

    public SerializableBufferedImage() {
        this.image = null;
    }

    public SerializableBufferedImage(BufferedImage image) {
        this();
        this.setImage(image);
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        ImageIO.write(this.getImage(), "png", new MemoryCacheImageOutputStream(out));
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.setImage(ImageIO.read(new MemoryCacheImageInputStream(in)));
    }

    public void clear() {
        System.out.println("canvas cleared");
        this.image = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
    }
}
