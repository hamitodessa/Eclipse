package OBS_C_2025;

import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.W32APIOptions;
import java.io.IOException;


public class FileVersionInfo
{
    interface Version extends Library {

        Version INSTANCE = (Version) Native.loadLibrary("Version", Version.class, W32APIOptions.UNICODE_OPTIONS);

        public int GetFileVersionInfoSizeW(String lptstrFilename, int dwDummy);

        public boolean GetFileVersionInfoW(String lptstrFilename, int dwHandle,
            int dwLen, Pointer lpData);

        public int VerQueryValueW(Pointer pBlock, String lpSubBlock,
            PointerByReference lplpBuffer, IntByReference puLen);

    }

    public static class VS_FIXEDFILEINFO extends com.sun.jna.Structure {
        public int dwSignature;
        public int dwStrucVersion;
        public int dwFileVersionMS;
        public int dwFileVersionLS;
        public int dwProductVersionMS;
        public int dwProductVersionLS;
        public int dwFileFlagsMask;
        public int dwFileFlags;
        public int dwFileOS;
        public int dwFileType;
        public int dwFileSubtype;
        public int dwFileDateMS;
        public int dwFileDateLS;

           public VS_FIXEDFILEINFO(com.sun.jna.Pointer p){
                super(p);
           }
    }
  } 