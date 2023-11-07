import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "filex_obs")
public class FileObs implements Serializable {
    private static final long serialVersionUID = 1L;

    private long extFileId;
    private long fileId;
    private String objectStorageStripeName;
    private PermanentFile babylonFile;

    public FileObs() {

    }

    @Column(name = "extfileid")
    public long getExtFileId() {
        return extFileId;
    }

    public void setExtFileId(long extfileId) {
        this.extFileId = extfileId;
    }

    @Id
    @Column(name = "fileId", unique = true, nullable = false)
    public Long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    @Column(name = "objectstoragestripename")
    public String getObjectStorageStripeName() {
        return objectStorageStripeName;
    }

    public void setObjectStorageStripeName(String objectStorageStripeName) {
        this.objectStorageStripeName = objectStorageStripeName;
    }

    @OneToOne
    @MapsId
    @JoinColumn(name = "fileId")
    public PermanentFile getBabylonFile() {
        return babylonFile;
    }

    public void setBabylonFile(PermanentFile babylonFile) {
        this.babylonFile = babylonFile;
    }
}
