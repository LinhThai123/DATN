package fithou.edu.vn.DoAnTotNghiep.file;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class CloundService implements IFileService{
    private final Cloudinary cloudinary;

    @Autowired
    public CloundService(CloudinaryConfig cloudinaryConfig) {
        this.cloudinary = new Cloudinary(cloudinaryConfig.getConfig());
    }
    @Override
    public String uploadFile(MultipartFile file) {
        try{
            var fileName = file.getOriginalFilename();
            assert fileName != null ;
            var newFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
            var params = ObjectUtils.asMap("public_id", "greentech/" + newFileName, "overwrite", true, "resource_type", "image");
            var uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
            return uploadResult.get("secure_url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteFile(String url) {
        try {
            var publicId = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
            cloudinary.uploader().destroy("greentech/" + publicId, ObjectUtils.emptyMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
