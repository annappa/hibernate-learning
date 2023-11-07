
import com.google.common.base.Preconditions;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "filex")
public class PermanentFile implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int DB_SIZE_FILENAME = 255;
    public static final int DB_SIZE_FILEPATH = 500;
    public static final int DB_SIZE_FILESTOREPATH = 2000;
    public static final int DB_SIZE_FILETYPE = 3;

    PermanentFile() {
    }

    public PermanentFile(String fileStorePath, String fullPathName, String fileName,
                         String fileType, long fileSize) {
        this(fileStorePath, fullPathName, fileName, fileType, fileSize, null, false);
    }

    @SuppressWarnings("PMD.ExcessiveParameterList")
    public PermanentFile(String fileStorePath, String fullPathName, String fileName,
                         String fileType, long fileSize, String md5, boolean isClientFileMd5) {
        Preconditions.checkArgument(fileStorePath.length() <= DB_SIZE_FILESTOREPATH, "fileStorePath '" + fileStorePath + "' is larger than " + DB_SIZE_FILESTOREPATH);
        this.fileStorePath = fileStorePath;

        this.fullPathName = abbreviate(fullPathName, DB_SIZE_FILEPATH);
        this.fileName = abbreviate(fileName, DB_SIZE_FILENAME);

        // it is safe to truncate the filetype to 3 chars as it is only used for the display icon
        this.fileType = StringUtils.substring(fileType, 0, DB_SIZE_FILETYPE);
        this.fileSize = fileSize;

        this.md5 = md5;
        this.clientFileMd5 = isClientFileMd5;
    }

    @SuppressWarnings("PMD.ExcessiveParameterList")
    public PermanentFile(long fileId, String fileStorePath, String fullPathName, String fileName,
                         String fileType, long fileSize) {
        this(fileStorePath, fullPathName, fileName, fileType, fileSize, null, false);
        this.fileId = fileId;
    }

    private static String abbreviate(String filename, int maxLength) {
        String newName = filename;
        if (StringUtils.isNotBlank(filename) && filename.length() > maxLength) {

            if (StringUtils.lastIndexOf(filename, '.') > StringUtils.lastIndexOf(filename, File.separatorChar)) {
                // If the string content is a file with or without path name
                String extension = StringUtils.substring(filename, StringUtils.lastIndexOf(filename, '.'));
                newName = StringUtils.abbreviate(filename, maxLength - extension.length()) + extension;
            } else {
                newName = StringUtils.abbreviate(filename, maxLength);
            }
        }
        return newName;
    }

    @Id
    //@GeneratedValue(generator = "longAsLongKeypool")
    private long fileId;

    @Column(name = "filename", nullable = false, length = DB_SIZE_FILENAME)
    private String fileName;

    @Column(name = "filetype", nullable = false, length = DB_SIZE_FILETYPE)
    private String fileType;

    @Column(name = "filesize")
    private long fileSize = -1;

    @Column(name = "filepath", nullable = false, length = DB_SIZE_FILEPATH)
    private String fullPathName;

    @Column(name = "filesystemstoragename", nullable = false, length = DB_SIZE_FILESTOREPATH)
    private String fileStorePath;

    @Column(name = "filemd5hash", nullable = true)
    private String md5;

    @Column(name = "isclientfilemd5hash")
    private boolean clientFileMd5;

    @Column(name = "lastupdated", insertable = false, updatable = false)
    private Date lastUpdated;

    private FileObs fileObs;

    public long getFileId() {
        return fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFullPathName() {
        return fullPathName;
    }

    public String getFileStorePath() {
        return fileStorePath;
    }

    public long getFileSize() {
        return fileSize < 0 ? new File(fileStorePath).length() : fileSize;
    }

    public String getMd5() {
        return md5;
    }

    public boolean isClientFileMd5() {
        return clientFileMd5;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setFullPathName(String fullPathName) {
        this.fullPathName = fullPathName;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setClientFileMd5(boolean clientFileMd5) {
        this.clientFileMd5 = clientFileMd5;
    }

    @OneToOne( mappedBy = "babylonFile", cascade = CascadeType.ALL)
    public FileObs getFileObs() {
        return fileObs;
    }

    public void setFileObs(FileObs fileObs) {
        this.fileObs = fileObs;
    }
}
