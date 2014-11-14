/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import com.anosym.utilities.currency.CurrencyCode;
import com.anosym.utilities.currency.CurrencyCodes;
import com.anosym.utilities.formatter.CurrencyFormatter;
import com.anosym.utilities.geocode.CountryCode;
import com.anosym.utilities.geocode.CountryCodes;
import com.anosym.utilities.geocode.CountryIpMapping;
import com.anosym.utilities.geocode.CountryIpMappings;
import com.anosym.utilities.geocode.Ipv4;
import com.anosym.utilities.geocode.google.GoogleGeocodeResponse;
import com.anosym.utilities.logging.UtilityLogger;
import com.anosym.vjax.VMarshaller;
import com.anosym.vjax.VXMLBindingException;
import com.anosym.vjax.v3.VObjectMarshaller;
import com.anosym.vjax.xml.VDocument;
import com.anosym.vjax.xml.VElement;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import net.sf.jmimemagic.Magic;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.SimpleXmlSerializer;
import org.htmlcleaner.TagNode;

/**
 *
 * @author Administrator
 */
public final class Utility {

    private static final String MUSIC_EXTENSIONS[] = {"2sf", "mpeg", "2sflib", "3ga", "4mp", "669", "6cm", "8cm", "8med", "8svx", "a2m", "a52", "aa",
                                                      "aa3", "aac", "aax", "ab", "abm", "abc", "acd", "ac3", "acd-zip", "acd-bak", "acp", "acm", "adg",
                                                      "act", "adts", "adt", "afc", "adv", "agr", "agm", "aifc", "aiff", "ahx", "aif", "akp", "al",
                                                      "aimppl", "ais", "alc", "all", "alac", "alaw", "amr", "ams", "als", "amf", "aob", "amz", "amxd",
                                                      "ams", "aria", "apl", "apf", "ape", "at3", "ase", "asd", "ariax", "aud", "au", "au", "atrac",
                                                      "ay", "b4s", "band", "bap", "aup", "avastsounds", "avr", "awb", "box", "brstm", "bun", "bwf",
                                                      "bcs", "bdd", "bidule", "bonk", "cda", "caff", "cdlx", "cdda", "bww", "bwg", "caf", "c01",
                                                      "cgrp", "cfxr", "ckb", "cidb", "cdr", "cdo", "cfa", "cel", "csh", "cts", "cpr", "cpt", "conform",
                                                      "copy", "ckf", "cmf", "dct", "ddt", "dcf", "dcm", "d00", "d01", "cwb", "cwp", "dm", "dls", "djr",
                                                      "dig", "dig", "dfc", "df2", "dewf", "ds2", "ds", "drg", "dra", "dmse", "dmsa", "dmf", "dmc",
                                                      "dwd", "dwa", "efa", "ear", "efk", "efe", "efs", "efq", "dsm", "dsf", "dss", "dsp", "dts", "dtm",
                                                      "dvf", "dtshd", "f2r", "f32", "f3r", "f4a", "f64", "far", "fda", "fdp", "efv", "emd", "emp",
                                                      "emx", "emy", "esps", "evr", "expressionmap", "fzv", "fzf", "fzb", "ftm", "gbproj", "g726",
                                                      "g723", "g721", "flp", "flac", "fff", "fev", "fsm", "fsb", "frg", "fls", "groove", "gsm", "gpx",
                                                      "gro", "hdp", "hma", "gsm", "h0", "gio", "gio", "gbs", "gig", "gpbank", "gpk", "gm", "gp5",
                                                      "iti", "it", "its", "itls", "ins", "imp", "isma", "ins", "igp", "iff", "imf", "igr", "hsb",
                                                      "hmi", "ics", "iaa", "koz", "kpl", "krz", "ksc", "kin", "kit", "kmp", "koz", "k25", "k26", "kar",
                                                      "kfn", "jam", "jam", "jo", "jo-7z", "m3u8", "m3u", "m1a", "lwv", "lvp", "lso", "lqt", "logic",
                                                      "lof", "la", "l", "ktp", "kt3", "kt2", "ksm", "ksf", "mka", "mlp", "minincsf", "miniusf", "midi",
                                                      "mini2sf", "mgv", "mid", "mdl", "med", "ma1", "mbr", "m4p", "m4r", "m4a", "m4b", "mt9", "mt2",
                                                      "msv", "mscz", "mtm", "mti", "mtf", "mte", "mui", "mu3", "mts", "mtp", "musa", "mus", "mus",
                                                      "mus", "mmp", "mo3", "mmf", "mmm", "mp1", "mp2", "mod", "mogg", "mpa", "mpc", "mp3", "mp_",
                                                      "mpu", "mscx", "mpdp", "mpga", "nki", "nkc", "nks", "nkm", "nml", "nkx", "npl", "note", "nrt",
                                                      "nra", "nsf", "nsa", "ntn", "nst", "nwc", "nvf", "mux", "mux", "muz", "mws", "mx3", "mx4", "mx5",
                                                      "mx5template", "mxl", "mxmf", "myr", "mzp", "nap", "nbs", "ncw", "nkb", "pd", "pcm", "pcg",
                                                      "pcast", "pca", "pbf", "pat", "pac", "pls", "pla", "pkf", "pk", "phy", "pho", "pek", "peak",
                                                      "oma", "omf", "ogg", "okt", "ofr", "oga", "obw", "odm", "ovw", "ovw", "ots", "ove", "opus",
                                                      "orc", "omg", "omx", "raw", "ram", "rbs", "rax", "r", "qcp", "ra", "r1m", "rgrp", "rfl", "rmf",
                                                      "rip", "rcy", "rbs", "rex", "record", "ppc", "ppcx", "prg", "prg", "plst", "ply", "pna", "pno",
                                                      "ptm", "pts", "ptx", "pvc", "psf", "psm", "psy", "ptf", "scs11", "sd", "sbk", "sc2", "sd2f",
                                                      "sdat", "sd", "sd2", "saf", "sam", "s3m", "s3z", "sbg", "sbi", "sap", "sb", "rtm", "rti", "rta",
                                                      "rso", "s3i", "rx2", "rvx", "rts", "rmx", "rmm", "rmj", "rmi", "rsn", "rol", "rns", "rng", "smp",
                                                      "snd", "snd", "snd", "sng", "sng", "sns", "sou", "shn", "sib", "sid", "slp", "slx", "sma", "smf",
                                                      "smp", "sf2", "sf", "sfk", "sfap0", "sfpack", "sfl", "sgp", "sfs", "sds", "sdii", "sdx", "sdt",
                                                      "seq", "seg", "sesx", "ses", "tm8", "tmc", "thx", "tm2", "tfmx", "tg", "tak", "td0", "syx",
                                                      "tak", "syn", "syw", "syh", "syn", "swav", "sxt", "swa", "sw", "svx", "svd", "sty", "sty", "stx",
                                                      "stm", "stap", "ssnd", "sseq", "sseq", "spx", "sprg", "sppack", "sph", "vlc", "vmd", "vmf",
                                                      "vmf", "vc3", "vdj", "vgm", "vgz", "vag", "val", "vap", "vb", "ust", "uw", "uwf", "v2m", "uni",
                                                      "ulw", "usflib", "usf", "ub", "uax", "ult", "ulaw", "txw", "tun", "u8", "u", "trak", "toc",
                                                      "tta", "tsp", "xa", "xa", "wwu", "wyz", "wvc", "wve", "wut", "wv", "xp", "xrns", "xmi", "xmz",
                                                      "xm", "xmf", "xfs", "xi", "zpa", "yookoo", "ym", "xwb", "xt", "xspf", "xsp", "xsb", "zvr", "zvd",
                                                      "zpl", "voxal", "vpl", "vpm", "vqf", "vmo", "voc", "voi", "vox", "w01", "w64", "wav", "wav",
                                                      "vrf", "vsq", "vtx", "vyf", "wfm", "wfd", "wma", "wfp", "wax", "wave", "wfb", "wem", "wtpl",
                                                      "wrk", "wus", "wtpt", "wpk", "wow", "wproj", "wpp"};
    private static final String VIDEO_EXTENSIONS[] = {"264", "3g2", "3gp", "3gp2", "3gpp", "3gpp2", "3mm", "3p2", "60d", "787", "890", "aaf", "aec",
                                                      "aep", "aepx", "aet", "ajp", "aetx", "am", "ale", "amv", "amc", "anim", "amx", "arcut", "aqt",
                                                      "asf", "arf", "avb", "asx", "avd", "avc", "avs", "avs", "avi", "avp", "bdm", "bdmv", "avv",
                                                      "axm", "bik", "bin", "bdt2", "bdt3", "bmk", "bnp", "bix", "bmc", "bvr", "bsf", "bs4", "box",
                                                      "camv", "camrec", "camproj", "byu", "cip", "cine", "cel", "ced", "cmproj", "cmmtpl", "cmmp",
                                                      "clpi", "cx3", "d2v", "d3v", "dat", "cmrec", "cpi", "cst", "cvc", "dcr", "ddat", "dif", "dir",
                                                      "dav", "dce", "dck", "dcr", "dmsm", "dmsd3d", "dmss", "dmsm3d", "dlx", "divx", "dmsd", "dmb",
                                                      "dsy", "dream", "dv-avi", "dv", "dnc", "dmx", "dpg", "dpa", "dzm", "dzp", "dvx", "dxr", "dvr",
                                                      "dvr-ms", "dv4", "dvdmedia", "f4v", "fbr", "ezt", "f4p", "evo", "eye", "dzt", "edl", "fli",
                                                      "flh", "flc", "ffd", "fcproject", "fcp", "fbz", "fbr", "gts", "grasp", "gom", "gl", "gfp", "ftc",
                                                      "flx", "flv", "irf", "ircp", "ismc", "ism", "ismv", "ismclip", "ivf", "iva", "gvp", "gvi",
                                                      "hdmov", "h264", "ifo", "hkm", "imovieproject", "imovieproj", "k3g", "kmv", "ktn", "lrec", "lsf",
                                                      "lsx", "m15", "m1pg", "ivr", "ivs", "izz", "izzy", "jmv", "jss", "jts", "jtv", "m75", "m4v",
                                                      "m4u", "m4e", "mj2", "mgv", "meta", "mani", "m2a", "m21", "m21", "m1v", "m2v", "m2ts", "m2t",
                                                      "m2p", "moi", "moov", "modd", "moff", "mp21", "mp21", "mov", "movie", "mk3d", "mkv", "mjp",
                                                      "mjpg", "mob", "mod", "mmv", "mnv", "mpls", "mpl", "mpv", "mpsub", "mpg2", "mpg", "mpl",
                                                      "mpgindex", "mpeg1", "mpf", "mpeg4", "mp4", "mp2v", "mpe", "mp4v", "mvex", "mvp", "mvp", "mvy",
                                                      "mvb", "mvc", "mvd", "mve", "msh", "mswmm", "mts", "mtv", "mpv2", "mqv", "msdvd", "mse", "pds",
                                                      "par", "pac", "otrkey", "osp", "ogx", "ogv", "ogm", "nvc", "nuv", "nut", "nsv", "ncor", "mys",
                                                      "mxv", "mxf", "psb", "psh", "prproj", "prtl", "prel", "pro", "pns", "ppj", "pmf", "pmv",
                                                      "playlist", "plproj", "piv", "pjs", "pgi", "photoshow", "rms", "rmp", "rmd", "rmd", "rp", "roq",
                                                      "rmvb", "rmv", "rum", "rts", "rts", "rsx", "sbk", "rvl", "rvid", "rv", "pvr", "pxv", "pssd",
                                                      "pva", "qtindex", "qtl", "qt", "qtch", "r3d", "rcd", "qtm", "qtz", "rec", "rm", "rcproject",
                                                      "rdb", "smv", "sml", "sqz", "spl", "ssf", "srt", "stl", "ssm", "stx", "str", "swf", "svi", "swt",
                                                      "swi", "tdx", "tda3mt", "sbt", "scc", "scm", "scm", "scn", "screenflow", "sec", "sedprj", "seq",
                                                      "sfd", "sfvidcap", "siv", "smi", "smi", "smil", "smk", "vem", "veg", "vdx", "vdr", "vdo", "vcv",
                                                      "vcr", "vcpf", "video", "vid", "vgz", "vfz", "vfw", "vft", "vf", "vep", "tpd", "tpr", "tp",
                                                      "tp0", "tix", "tod", "thp", "tivo", "usm", "vc1", "tvs", "usf", "tsp", "ttxt", "trp", "ts",
                                                      "wmx", "wmv", "wp3", "wot", "wm", "wlmp", "wmmp", "wmd", "xel", "xej", "xfl", "xesc", "wtv",
                                                      "wpl", "wvx", "wve", "vob", "vp3", "vp6", "vp7", "viewlet", "viv", "vivo", "vlab", "vsp", "w32",
                                                      "wcp", "webm", "vpj", "vro", "vs4", "vse", "zmv", "zm3", "zm2", "y4m", "xvid", "xmv", "xlmv",
                                                      "zm1", "zeg", "yuv", "yog"};
    private static CountryCodes countryCodesCache;
    private static CurrencyCodes currencyCodesCache;

    static {
        Arrays.sort(MUSIC_EXTENSIONS);
        Arrays.sort(VIDEO_EXTENSIONS);
    }

    private static File getIpMappingDirectory() {
        File file = new File("ip_mappings");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private static File getIpMappingFile(int firstOctet, int secondOctet) {
        File file = new File(getIpMappingDirectory(), firstOctet + "");
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, secondOctet + ".xml");
    }

    private static void saveCountryIpMappings(CountryIpMappings countryIpMappingsCache, int firstOctet, int secondOctet) {
        try {
            File f = getIpMappingFile(firstOctet, secondOctet);
            VDocument doc = new VDocument(f);
            doc.setRootElement(new VMarshaller<CountryIpMappings>().marshall(countryIpMappingsCache));
            doc.writeDocument();
        } catch (VXMLBindingException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static boolean countryIpMappingsLoaded(int firstOctet) {
        File file = new File(getIpMappingDirectory(), firstOctet + ".xml");
        return file.exists();
    }

    public static CountryIpMappings getCountryIpMappings(int firstOctet, int secondOctet) {
        try {
            File file = getIpMappingFile(firstOctet, secondOctet);
            if (!file.exists()) {
                return null;
            }
            InputStream inn = new FileInputStream(file);
            VDocument doc = VDocument.parseDocument(inn);
            return new VMarshaller<CountryIpMappings>().unmarshall(doc);
        } catch (Exception ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void loadCountryIpMappings() {
        try {
            CountryIpMappings countryIpMappingsCache = new CountryIpMappings();
            InputStream inn = Utility.class.getResourceAsStream("/com/anosym/utilities/geocode/GeoIPCountryWhois.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inn));
            String line = null;
            int firstOctet = 0;
            int secondOctet = 0;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                UtilityLogger.fine("ip mapping: " + line);
                String mappings[] = line.split(",");
                if (mappings.length == 6) {
                    //get the range and the country code
                    String origin = mappings[0].trim();
                    origin = origin.substring(1, origin.length() - 1).trim();
                    String end = mappings[1].trim();
                    end = end.substring(1, end.length() - 1).trim();
                    String code = mappings[4].trim();
                    code = code.substring(1, code.length() - 1).trim();
                    CountryCode countryCode = Utility.findCountryCodeFromCountryIsoCode(code);
                    if (countryCode != null) {
                        CountryIpMapping cim = new CountryIpMapping(countryCode, origin, end);
                        if (countryIpMappingsLoaded(cim.getIpv4Range().getOrigin().getFirstOctet())) {
                            continue;
                        }
                        if ((cim.getIpv4Range().getOrigin().getFirstOctet() != firstOctet && firstOctet > 0)
                                || (cim.getIpv4Range().getOrigin().getSecondOctet() != secondOctet && secondOctet > 0)) {
                            saveCountryIpMappings(countryIpMappingsCache, firstOctet, secondOctet);
                            countryIpMappingsCache = new CountryIpMappings();
                        }
                        firstOctet = cim.getIpv4Range().getOrigin().getFirstOctet();
                        secondOctet = cim.getIpv4Range().getOrigin().getSecondOctet();
                        countryIpMappingsCache.addCountryIpMapping(cim);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns true if the supplied string is a valid number in base 10, without throwing an exception.
     * <p>
     * @param number - the String to check
     * @return
     */
    public static boolean isNumber(String number) {
        if (isNullOrEmpty(number)) {
            return false;
        }
        boolean firstChar = true;
        boolean decimalPoint = false;
        for (char c : number.toCharArray()) {
            try {
                if ((c == '-' || c == '+') && firstChar) {
                    continue;
                }
                if (c == '.' && !decimalPoint) {
                    decimalPoint = true;
                    continue;
                }
                if (!Character.isDigit(c)) {
                    return false;
                }
            } finally {
                firstChar = false;
            }
        }
        return true;
    }

    public static String unquote(String quotedString) {
        quotedString = quotedString.trim();
        if (quotedString.indexOf("\"") == 0 && quotedString.lastIndexOf("\"") == quotedString.length() - 1) {
            return quotedString.substring(1, quotedString.length() - 1);
        }
        return quotedString;
    }

    public static <T extends Serializable> byte[] toByteArray(T data) throws IOException {
        ByteArrayOutputStream out = null;
        ObjectOutputStream objOut = null;
        try {
            out = new ByteArrayOutputStream();
            objOut = new ObjectOutputStream(out);
            objOut.writeObject(data);
            return out.toByteArray();
        } finally {
            if (objOut != null) {
                objOut.close();
            }
        }
    }

    public static double roundOff(double a, int dp) {
        String aa = "" + a;
        if (!aa.contains(".")) {
            return a;
        }
        String decP = aa.substring(aa.indexOf('.') + 1);
        if (decP.length() <= dp) {
            return a;
        }
        char c = decP.charAt(dp);
        int v = Character.getNumericValue(c);
        if (v >= 5) {
            a = a + (Math.signum(a) * Math.pow(0.1, dp));
            aa = "" + a;
        }
        if (aa.substring(aa.indexOf('.') + 1).length() > dp) {
            //truncate
            aa = aa.substring(0, aa.indexOf('.') + dp + 1);
        }
        return Double.valueOf(aa);
    }

    /**
     * Returns a value between 0-100 indicating the match percentage of the pattern as it appears in the string
     * <p>
     * @param pattern
     * @param word
     * @return
     */
    public static int matchPercentage(String _pattern, String _word) {
        String pattern = _pattern.toLowerCase();
        String word = _word.toLowerCase();
        int pc = 0;
        int ratio = 100 / pattern.length() + 1;
        //we must find where it starts
        //school;
        int prevIdx = 0;
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            int idx = word.indexOf(c, prevIdx);
            if (idx == 0 || (idx - prevIdx) == 1) { //start or they follow each other
                pc += ratio;
                prevIdx = idx;
            } else if (idx == prevIdx) { //repetition
                prevIdx++;
                pc += ratio;
            }
        }
        return pc;
    }

    /**
     * Gets the current working directory, relative to the specified class
     * <p>
     * @param mainClass
     * @return
     */
    public static String getCurrentWorkingDirectory(Class mainClass) {
        String name = mainClass.getName().replace('.', '/');
        String s = mainClass.getResource("/" + name + ".class").toString();
        s = s.replace('/', File.separatorChar);
        if (s.contains(".jar")) {
            s = s.substring(0, s.indexOf(".jar") + 4);
        }
        s = s.substring(s.lastIndexOf(':') - 1);
        s = s.replaceAll("%20", " ");
        s = s.substring(0, s.lastIndexOf(File.separatorChar) + 1);
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("linux") || os.toLowerCase().contains("ubuntu")) {
            s = s.substring(s.indexOf(":") + 1);
        }
        return s;
    }

    /**
     * This works when the project was compiled with netbeans, where commonly, netbeans places all libraries at /lib folder
     * <p>
     * @return
     */
    public static String getCurrentWorkingDirectory() {
        Class mainClass = Utility.class;
        String name = mainClass.getName().replace('.', '/');
        String s = mainClass.getResource("/" + name + ".class").toString();
        s = s.replace('/', File.separatorChar);
        if (s.contains(".jar")) {
            s = s.substring(0, s.indexOf(".jar") + 4);
        }
        s = s.substring(s.lastIndexOf(':') - 1);
        s = s.replaceAll("%20", " ");
        s = s.substring(0, s.lastIndexOf(File.separatorChar) + 1);
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("linux") || os.toLowerCase().contains("ubuntu")) {
            s = s.substring(s.indexOf(":") + 1);
        }
        File file = new File(s);
        return file.getParent();
    }

    /**
     * Returns the home directory based on the current user settings.
     * <p>
     * @return
     */
    public static String getHomeDirectory() {
        File file = new File(System.getProperty("user.home"));
        String homeDir = file.getParent();
        if (homeDir.startsWith("/")) {
            //be sure we are not running as root in linux
            if (homeDir.trim().equals("/")) {
                homeDir = "/home";
            }
        }
        Logger.getLogger(Utility.class.getCanonicalName()).log(Level.INFO, "Home Dir: {0}", homeDir);
        return homeDir;
    }

    public static String getUserHomeDirectory() {
        File file = new File(System.getProperty("user.home"));
        return file.getAbsolutePath();
    }

    /**
     * Returns the installation partition On windows this returns the disk drive the software is currently running
     * <p>
     * @return
     */
    public static String getInstallationPartion(Class clazz) {
        String wDir = getCurrentWorkingDirectory(clazz);
        return wDir.substring(0, wDir.indexOf(":" + File.separatorChar) + 2);
    }

    /**
     * @see #getCurrentWorkingDirectory()
     * @return
     */
    public static String getInstallationPartion() {
        String wDir = getCurrentWorkingDirectory();
        return wDir.substring(0, wDir.indexOf(":" + File.separatorChar) + 2);
    }

    /**
     * Returns the degree of correlation between the two strings
     * <p>
     * @param matcher
     * @param pattern
     * @return
     */
    public static int correlation(String matcher, String pattern) {
        if (matcher.trim().equalsIgnoreCase(pattern.trim())) {
            return 100;
        }
        //find correlation
        String[] matcherTokens = matcher.split(" ");
        String[] patterToken = pattern.split(" ");
        int corr = 0;
        int i = 0, j = 0;
        for (; i < matcherTokens.length && j < patterToken.length; j++, i++) {
            String s = matcherTokens[i];
            String ss = patterToken[j];
            corr += matchPercentage(matcherTokens[i], patterToken[j]);
            int d = Math.abs(s.length() - ss.length());
            if (d > 0) {
                corr -= (corr / d); //take care of length difference
            }
        }
        //must be divided by the weight of the smallest of j, and i
        int small = Math.min(i, j);
        if (small > 0) {
            corr /= small;
        }
        int diff = matcherTokens.length + patterToken.length;
        return (corr - diff);
    }

    /**
     * Returns true if and only the data contains at least one member from the info array
     * <p>
     * @param data
     * @param info
     * @return
     */
    public static boolean containsAMember(String[] data, String[] info) {
        String[] tmpData = Arrays.copyOf(data, data.length);
        Arrays.sort(tmpData);
        for (String _if : info) {
            int i = -1;
            if ((i = Arrays.binarySearch(tmpData, _if)) > -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if and only if the data contains all members from the info array
     * <p>
     * @param data
     * @param info
     * @return
     */
    public static boolean containsAllMembers(String[] data, String[] info) {
        String[] tmpData = Arrays.copyOf(data, data.length);
        Arrays.sort(tmpData);
        for (String _if : info) {
            if (Arrays.binarySearch(tmpData, _if) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a worded number to integer
     * <p>
     * @param number
     * @return
     */
    public static int toNumber(String number) {
        if (isNumber(number)) {
            return Integer.parseInt(number);
        }
        //otherwise we must form a regular expression
        String regex = "\beleven$.{3,5}(teen{1})";
        return 0;
    }

    public static int value(String expression) {
        expression = expression.trim();
        if (expression.equalsIgnoreCase("one")) {
            return 1;
        }
        if (expression.equalsIgnoreCase("two")) {
            return 2;
        }
        if (expression.equalsIgnoreCase("three")) {
            return 3;
        }
        if (expression.equalsIgnoreCase("four")) {
            return 4;
        }
        if (expression.equalsIgnoreCase("five")) {
            return 5;
        }
        if (expression.equalsIgnoreCase("six")) {
            return 6;
        }
        if (expression.equalsIgnoreCase("seven")) {
            return 7;
        }
        if (expression.equalsIgnoreCase("eight")) {
            return 8;
        }
        if (expression.equalsIgnoreCase("nine")) {
            return 9;
        }
        if (expression.equalsIgnoreCase("ten")) {
            return 10;
        }
        if (expression.equalsIgnoreCase("eleven")) {
            return 11;
        }
        if (expression.equalsIgnoreCase("twelve")) {
            return 12;
        }
        if (expression.equalsIgnoreCase("thirteen")) {
            return 13;
        }
        if (expression.equalsIgnoreCase("fourteen")) {
            return 14;
        }
        if (expression.equalsIgnoreCase("fifteen")) {
            return 15;
        }
        if (expression.equalsIgnoreCase("sixteen")) {
            return 16;
        }
        if (expression.equalsIgnoreCase("seventeen")) {
            return 17;
        }
        if (expression.equalsIgnoreCase("eighteen")) {
            return 18;
        }
        if (expression.equalsIgnoreCase("nineteen")) {
            return 19;
        }
        if (expression.equalsIgnoreCase("twenty")) {
            return 20;
        }
        if (expression.equalsIgnoreCase("thirty")) {
            return 30;
        }
        if (expression.equalsIgnoreCase("forty") || expression.equalsIgnoreCase("fourty")) {
            return 40;
        }
        if (expression.equalsIgnoreCase("fifty")) {
            return 50;
        }
        if (expression.equalsIgnoreCase("sixty")) {
            return 60;
        }
        if (expression.equalsIgnoreCase("seventy")) {
            return 70;
        }
        if (expression.equalsIgnoreCase("eighty")) {
            return 80;
        }
        if (expression.equalsIgnoreCase("ninety")) {
            return 90;
        }
        if (expression.equalsIgnoreCase("hundred")) {
            return 100;
        }
        if (expression.equalsIgnoreCase("thousand")) {
            return 1000;
        }
        if (expression.equalsIgnoreCase("million")) {
            return 1000000;
        }
        if (expression.equalsIgnoreCase("billion")) {
            return 1000000000;
        }
        return 0;
    }

    /**
     * Assumes that the list is sorted
     * <p>
     * @param <T>
     * @param <V>
     * @param list
     * @param comp
     * @return
     */
    public static <T, V> T find(List<T> list, VComparator<T, V> comp, V value) {
//    for (T t : list) {
//      if (comp.compare(t, value) == 0) {
//        return t;
//      }
//    }
//    return null;
        return find(list, comp, value, 0, list.size());
    }

    private static <T, V> T find(List<T> list, VComparator<T, V> comp, V value, int start, int end) {
        if (start == end) {
            return null;
        }
        int middle = (start + end) / 2;
        T mid = list.get(middle);
        int res = comp.compare(mid, value);
        if (res == 0) {
            return mid;
        } else if (res < 0) {
            return find(list, comp, value, middle + 1, end);
        } else {
            return find(list, comp, value, 0, middle);
        }
    }

    /**
     * The following algorithm assumes that the list is sorted
     * <p>
     * @param <T>
     * @param list
     * @param index
     * @param cmp
     * @return
     */
    public static <T> int findPossibleIndex(List<T> list, T index, Comparator<T> cmp) {
        return findPossibleIndex(list, index, cmp, 0, list.size());
    }

    private static <T> int findPossibleIndex(List<T> list, T t, Comparator<T> cmp, int start, int end) {
        if (list.isEmpty()) {
            return 0;
        }
        if (start == end) {
            return start;
        }
        if (end < start) {
            return start;
        }
        int mid = (start + end) / 2;
        T mV = list.get(mid);
        int c = cmp.compare(t, mV);
        if (c == 0) {
            return mid;
        }
        if (c > 0) {
            return findPossibleIndex(list, t, cmp, mid + 1, end);
        } else {
            return findPossibleIndex(list, t, cmp, 0, mid - 1);
        }
    }

    /**
     * Removes all elements from the specified list, that do not meet the selector constraint.
     * <p>
     * @param <T>
     * @param lists
     * @param selector
     */
    public static <T> void select(List<T> lists, Selector<T> selector) {
        for (ListIterator<T> it = lists.listIterator(); it.hasNext();) {
            if (!selector.select(it.next())) {
                it.remove();
            }
        }
    }

    /**
     * Returns a list of filtered element based on the selector and the list values.
     * <p>
     * @param <T>
     * @param <R>
     * @param lists
     * @param selector
     * @return
     */
    public static <T, R> List<R> filter(List<T> lists, SelectionFilter<T, R> selector) {
        List<R> filters = new ArrayList<R>();
        for (ListIterator<T> it = lists.listIterator(); it.hasNext();) {
            R value = selector.filter(it.next());
            if (value != null) {
                filters.add(value);
            }
        }
        return filters;
    }

    /**
     * Returns the first element in the list that meets the selector constraint.
     * <p>
     * @param <T>
     * @param list
     * @param selector
     * @return
     */
    public static <T> T find(List<T> list, Selector<T> selector) {
        for (ListIterator<T> it = list.listIterator(); it.hasNext();) {
            T t = it.next();
            if (selector.select(t)) {
                return t;
            }
        }
        return null;
    }

    public static <T> boolean contains(List<T> list, T key, Comparator<T> comparator) {
        List<T> myList = new ArrayList<T>(list);
        Collections.sort(myList, comparator);
        int index = Collections.binarySearch(myList, key, comparator);
        return index > -1;
    }

    public static String reverse(String reverse) {
        String s = "";
        for (int i = reverse.length() - 1; i >= 0; i--) {
            s += reverse.charAt(i);
        }
        return s;
    }

    public static int parseInt(String s, int radix) {
        int value = Integer.parseInt(s, radix);
        if (radix == 2 && s.startsWith("1")) {
            return ~value + 1;
        }
        return value;
    }

    public static int complement2(int value) {
        UtilityLogger.fine(Integer.toString(value, 2));
        UtilityLogger.fine(Integer.toString(~value, 2));
        return value;
    }

    /**
     * Returns the size of the image in a 2 length array, with [0] = width, and [1] = height. This methods returns an array with {-1, -1} if it cannot
     * determine the size of the image.
     * <p>
     * @param fileName
     * @return
     */
    public static int[] getImageSize(String fileName) {
        int[] size = {-1, -1};
        try {
            BufferedImage image = ImageIO.read(new File(fileName));
            if (image != null) {
                size[0] = image.getWidth();
                size[1] = image.getHeight();
            }
        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return size;
    }

    public static boolean isImageFile(String fileName) {
        fileName = fileName.toLowerCase();
        return fileName.endsWith(".raw")
                || fileName.endsWith(".png")
                || fileName.endsWith(".gif")
                || fileName.endsWith(".bmp")
                || fileName.endsWith(".tiff")
                || fileName.endsWith(".exif")
                || fileName.endsWith(".jfif")
                || fileName.endsWith(".jpeg")
                || fileName.endsWith(".jpeg2000")
                || fileName.endsWith(".jpg")
                || fileName.endsWith(".ppm")
                || fileName.endsWith(".pgm")
                || fileName.endsWith(".pbm")
                || fileName.endsWith(".pnn");
    }

    public static boolean isMusicFile(String fileName) {
        String mimeType = getMimeType(fileName);
        if (mimeType != null) {
            int i = mimeType.lastIndexOf("/");
            if (i > -1) {
                String ext = mimeType.substring(i + 1);
                return Arrays.binarySearch(MUSIC_EXTENSIONS, ext) > -1;
            }
        }
        return false;
    }

    public static boolean isVideoFile(String fileName) {
        String mimeType = getMimeType(fileName);
        if (mimeType != null) {
            int i = mimeType.lastIndexOf("/");
            if (i > -1) {
                String ext = mimeType.substring(i + 1);
                return Arrays.binarySearch(VIDEO_EXTENSIONS, ext) > -1;
            }
        }
        return false;
    }

    public static boolean isMusicOrVideoFile(String fileName) {
        return isMusicFile(fileName) || isVideoFile(fileName);
    }

    public static String[] toArrayString(String str) {
        str = str.trim();
        if (str.startsWith("[") && str.endsWith("]")) {
            str = str.substring(1, str.length() - 1);
            //split
            return str.split(",");
        }
        return null;
    }

    public static byte[] fromString(String str) {
        str = str.trim();
        if (str.indexOf('[') == 0 && str.lastIndexOf(']') == str.length() - 1) {
            String trimStr = str.substring(1, str.length() - 1);
            String[] values = trimStr.split(",");
            byte[] bb = new byte[values.length];
            int i = 0;
            for (String s : values) {
                s = s.trim();
                byte b = Byte.parseByte(s);
                bb[i++] = b;
            }
            return bb;
        }
        return null;
    }

    public static boolean isValidWebUrl(String value) {
        if (value == null) {
            return false;
        }
        try {
            if (!value.startsWith("http")) {
                value = "http://" + value;
            }
            URL url = new URL(value);
            return true;
        } catch (MalformedURLException ex) {
            return false;
        }
    }

    public static String getMimeType(String fileUrl) {
        try {
            return Magic.getMagicMatch(new File(fileUrl), false).getMimeType();
        } catch (Exception ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * All paths are absolute.
     * <p>
     * @param files
     * @param zipFile
     */
    public static void zipFiles(String[] files, String zipFile) {
        ZipOutputStream zos = null;
        try {
            OutputStream out = new FileOutputStream(new File(zipFile));
            zos = new ZipOutputStream(out);
            for (String file : files) {
                zipFiles("", new File(file), zos);
            }
        } catch (Exception e) {
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException ex) {
                    Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * All paths are absolute.
     * <p>
     * @param files
     * @param zipFile
     */
    public static void zipFiles(String[] files, String zipFile, Map<String, String> mappedNames) {
        ZipOutputStream zos = null;
        try {
            OutputStream out = new FileOutputStream(new File(zipFile));
            zos = new ZipOutputStream(out);
            for (String file : files) {
                zipFiles("", new File(file), zos, mappedNames);
            }
        } catch (Exception e) {
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException ex) {
                    Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void zipFiles(String relativeBaseUrl, File file, ZipOutputStream zipOut, Map<String, String> mappedNames) {
        String mappedName = mappedNames.get(file.getAbsolutePath());
        if (mappedName != null) {
            relativeBaseUrl += mappedName;
        } else {
            relativeBaseUrl += file.getName();
        }
        if (file.isDirectory()) {
            relativeBaseUrl += File.separator;
            try {
                zipOut.putNextEntry(new ZipEntry(relativeBaseUrl));
                File[] list = file.listFiles();
                for (File f : list) {
                    zipFiles(relativeBaseUrl, f, zipOut, mappedNames);
                }
                zipOut.closeEntry();
            } catch (IOException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                zipOut.putNextEntry(new ZipEntry(relativeBaseUrl));
                copy(file, zipOut);
                zipOut.closeEntry();
            } catch (IOException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void zipFiles(String relativeBaseUrl, File file, ZipOutputStream zipOut) {
        if (file.isDirectory()) {
            try {
                relativeBaseUrl += file.getName() + "/";
                zipOut.putNextEntry(new ZipEntry(relativeBaseUrl));
                File[] list = file.listFiles();
                for (File f : list) {
                    zipFiles(relativeBaseUrl, f, zipOut);
                }
            } catch (IOException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                relativeBaseUrl += file.getName();
                zipOut.putNextEntry(new ZipEntry(relativeBaseUrl));
                copy(file, zipOut);
                zipOut.closeEntry();
            } catch (IOException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        while (true) {
            int readCount = in.read(buffer);
            if (readCount < 0) {
                break;
            }
            out.write(buffer, 0, readCount);
        }
    }

    private static void copy(File file, OutputStream out) throws IOException {
        InputStream in = new FileInputStream(file);
        try {
            copy(in, out);
        } finally {
            in.close();
        }
    }

    private static void copy(InputStream in, File file) throws IOException {
        OutputStream out = new FileOutputStream(file);
        try {
            copy(in, out);
        } finally {
            out.close();
        }
    }

    public static List<CountryCode> getAfricanCountryCode() {
        return loadCountryCodes().getAfricaCountryCodes();
    }

    public static List<CountryCode> getCountryCodes() {
        return loadCountryCodes().getCountryCodes();
    }

    public static boolean isAfricanCountry(String isoCode) {
        return CountryCodes.AFRICA_ISO_COUNTRY_CODES.contains(isoCode);
    }

    public static boolean isEuropeanCountry(String isoCode) {
        return CountryCodes.EUROPEAN_ISO_COUNTRY_CODES.contains(isoCode);
    }

    public static String getCountryCodeFromIp(String ip_address, String license_key) {
        BufferedReader in = null;
        try {
            String url_str = "http://geoip.maxmind.com/e?l=" + license_key + "&i=" + ip_address;
            URL url = new URL(url_str);
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inLine;
            while ((inLine = in.readLine()) != null) {
                // Alternatively use a CSV parser here.
                Pattern p = Pattern.compile("\"([^\"]*)\"|(?<=,|^)([^,]*)(?:,|$)");
                Matcher m = p.matcher(inLine);
                ArrayList<String> fields = new ArrayList<String>();
                String f;
                while (m.find()) {
                    f = m.group(1);
                    if (f != null) {
                        fields.add(f);
                    } else {
                        fields.add(m.group(2));
                    }
                }
                return fields.get(0);

            }
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static CountryCodes loadCountryCodes() {
        if (countryCodesCache != null) {
            return countryCodesCache;
        }
        try {
            VMarshaller<CountryCodes> m = new VMarshaller<CountryCodes>();
            VDocument doc = VDocument.parseDocument(Utility.class.getResourceAsStream("/com/anosym/utilities/geocode/country_codes.xml"));
            return (countryCodesCache = m.unmarshall(doc));
        } catch (VXMLBindingException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static CurrencyCodes loadCurrencyCodes() {
        if (currencyCodesCache == null) {
            try {
                VMarshaller<CurrencyCodes> m = new VMarshaller<CurrencyCodes>();
                VDocument doc = VDocument.parseDocument(Utility.class.getResourceAsStream("/com/anosym/utilities/currency/currency_codes.xml"));
                currencyCodesCache = m.unmarshall(doc);
            } catch (VXMLBindingException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return currencyCodesCache;
    }

    public static CountryCode findCountryCodeFromCountryName(String countryName) {
        CountryCodes codes = loadCountryCodes();
        if (codes != null) {
            for (CountryCode cc : codes.getCountryCodes()) {
                if (cc.getName().equalsIgnoreCase(countryName)) {
                    return cc;
                }
            }
        }
        return null;
    }

    public static CurrencyCode findCurrencyCodeFromCountryName(String countryName) {
        CurrencyCodes codes = loadCurrencyCodes();
        if (codes != null) {
            for (CurrencyCode cc : codes.getCurrencyCodes()) {
                if (cc.getCountryCode().getName().equalsIgnoreCase(countryName)) {
                    return cc;
                }
            }
        }
        return null;
    }

    public static CurrencyCode findCurrencyCodeFromCountryIsoCode(String isoCode) {
        CurrencyCodes codes = loadCurrencyCodes();
        if (codes != null) {
            for (CurrencyCode cc : codes.getCurrencyCodes()) {
                if (cc.getCountryCode().getIsoCode().equalsIgnoreCase(isoCode)
                        || cc.getCountryCode().getIsoCode2().equalsIgnoreCase(isoCode)) {
                    return cc;
                }
            }
        }
        return null;
    }

    public static CurrencyCode findCurrencyCodeFromCountryIsoCodeAndCurrencySymbol(
            String countryIsoCode,
            String currencySymbol) {
        CurrencyCodes codes = loadCurrencyCodes();
        if (codes != null) {
            for (CurrencyCode cc : codes.getCurrencyCodes()) {
                if (cc.getCurrencySymbol().equalsIgnoreCase(currencySymbol) && (cc.getCountryCode().getIsoCode().equalsIgnoreCase(countryIsoCode)
                        || cc.getCountryCode().getIsoCode2().equalsIgnoreCase(countryIsoCode))) {
                    return cc;
                }
            }
        }
        return null;
    }

    public static final CurrencyCodes getCurrencyCodes() {
        return loadCurrencyCodes();
    }

    public static CurrencyCode findCurrencyCodeFromCurrencySymbol(String currencySymbol) {
        CurrencyCodes codes = loadCurrencyCodes();
        if (codes != null) {
            for (CurrencyCode cc : codes.getCurrencyCodes()) {
                if (cc.getCurrencySymbol().equalsIgnoreCase(currencySymbol)) {
                    return cc;
                }
            }
        }
        return null;
    }

    public static Currency convertCurrencyCodeToCurrency(CurrencyCode currencyCode) {
        return Currency.getInstance(currencyCode.getCurrencySymbol());
    }

    public static Currency[] getAvailableCurrencies() {
        return getUniqueAvailableCurrencies();
    }

    public static Currency[] getUniqueAvailableCurrencies() {
        CurrencyCodes ccs = getAvailableCurrencyCodes();
        Currency[] curss = new Currency[ccs.getCurrencyCodes().size()];
        int i = 0;
        for (CurrencyCode cc : ccs.getCurrencyCodes()) {
            try {
                curss[i++] = convertCurrencyCodeToCurrency(cc);
            } catch (Exception e) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        Set<Currency> cs = new HashSet<Currency>();
        cs.addAll(Arrays.asList(curss));
        return cs.toArray(new Currency[0]);
    }

    public static CurrencyCodes getAvailableCurrencyCodes() {
        return loadCurrencyCodes();
    }

    public static CountryCode findCountryCodeFromCountryIsoCode(String isoCode) {
        CountryCodes codes = loadCountryCodes();
        if (codes != null) {
            for (CountryCode cc : codes.getCountryCodes()) {
                if (cc.getIsoCode().equalsIgnoreCase(isoCode) || cc.getIsoCode2().equalsIgnoreCase(isoCode)) {
                    return cc;
                }
            }
        }
        return null;
    }

    public static CountryCode findCountryCodeFromIpAddress(String ipAddress) {
        Ipv4 ip = new Ipv4(ipAddress);
        CountryIpMappings cims = null;
        int secondOctet = ip.getSecondOctet();
        while (cims == null && secondOctet > 0) {
            //if we dont find the file with second octet,
            //then we decrease the second octet value and find the file.
            cims = getCountryIpMappings(ip.getFirstOctet(), secondOctet--);
        }
        if (cims != null) {
            CountryIpMapping cim = cims.findCountryIpMapping(ip);
            if (cim != null) {
                return cim.getCountryCode();
            }
        }
        return null;
    }

    public static CountryCode findCountryCodeFromCountryInternationalDialingCode(String dialingCode) {
        CountryCodes codes = loadCountryCodes();
        if (codes != null) {
            for (CountryCode cc : codes.getCountryCodes()) {
                if (cc.getInternationalDialingCode().equalsIgnoreCase(dialingCode)) {
                    return cc;
                }
            }
        }
        return null;
    }

    /**
     * Returns the international dialing code for the iso
     * <p>
     * @param countryIsoCode
     * @return
     */
    public static String getInternationalCountryCode(String countryIsoCode) {
        return null;
    }

    public static String getCurrentLocation(String lat, String lon) {
        UtilityLogger.fine("lat: " + lat + "\nLon: " + lon);
        if (lat != null && lon != null) {
            try {
                //get country from google map
                String urlStr = "http://maps.googleapis.com/maps/api/geocode/xml?latlng=" + lat + "," + lon + "&sensor=false";
                URL url = new URL(urlStr);
                URLConnection urlConnection = url.openConnection();
                InputStream inn = urlConnection.getInputStream();
                VDocument doc = VDocument.parseDocument(inn);
                VElement root = doc.getRootElement();
                VElement status = root.findChild("status");
                UtilityLogger.fine(doc.toXmlString());
                if (status != null && "OK".equalsIgnoreCase(status.toContent())) {
                    //good result
                    VElement anyResult = root.findChild("result");
                    if (anyResult != null) {
                        VElement formattedAddress = anyResult.findChild("formatted_address");
                        String location = formattedAddress != null ? formattedAddress.toContent() : "";
                        VElement neighborhood = anyResult.findChild("address_component", "type", "neighborhood");
                        if (neighborhood != null) {
                            //find the value
                            VElement neighName = neighborhood.findChild("long_name");
                            if (neighName != null) {
                                location = neighName.toContent() + " " + location;
                            }
                        }
                        return location;
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static GoogleGeocodeResponse getCurrentLocationFromGoogle(String lat, String lon) {
        UtilityLogger.fine("lat: " + lat + "\nLon: " + lon);
        if (lat != null && lon != null) {
            try {
                //get country from google map
                String urlStr = "http://maps.googleapis.com/maps/api/geocode/xml?latlng=" + lat + "," + lon + "&sensor=false";
                URL url = new URL(urlStr);
                URLConnection urlConnection = url.openConnection();
                InputStream inn = urlConnection.getInputStream();
                VDocument doc = VDocument.parseDocument(inn);
                UtilityLogger.fine(doc.toXmlString());
                VObjectMarshaller<GoogleGeocodeResponse> vom = new VObjectMarshaller<GoogleGeocodeResponse>(GoogleGeocodeResponse.class);
                return vom.unmarshall(doc);
            } catch (Exception ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static GoogleGeocodeResponse getCurrentLocationFromGoogle(String address) {
        UtilityLogger.info("Address: " + address);
        if (address != null) {
            try {
                address = address.replaceAll("\\W+", "+");
                //get country from google map
                String urlStr = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + address + "&sensor=false";
                URL url = new URL(urlStr);
                URLConnection urlConnection = url.openConnection();
                InputStream inn = urlConnection.getInputStream();
                VDocument doc = VDocument.parseDocument(inn);
                UtilityLogger.fine(doc.toXmlString());
                VObjectMarshaller<GoogleGeocodeResponse> vom = new VObjectMarshaller<GoogleGeocodeResponse>(GoogleGeocodeResponse.class);
                return vom.unmarshall(doc);
            } catch (Exception ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static String getCurrentCountryCode(String lat, String lon) {
        UtilityLogger.info("lat: " + lat + "\nLon: " + lon);
        if (lat != null && lon != null) {
            try {
                //get country from google map
                String urlStr = "http://maps.googleapis.com/maps/api/geocode/xml?latlng=" + lat + "," + lon + "&sensor=false";
                URL url = new URL(urlStr);
                URLConnection urlConnection = url.openConnection();
                InputStream inn = urlConnection.getInputStream();
                VDocument doc = VDocument.parseDocument(inn);
                VElement root = doc.getRootElement();
                VElement status = root.findChild("status");
                if (status != null && "OK".equalsIgnoreCase(status.toContent())) {
                    //good result
                    VElement anyResult = root.findChild("result");
                    if (anyResult != null) {
                        VElement countryElement = anyResult.findChild("address_component", "type", "country");
                        if (countryElement != null) {
                            //find the value
                            VElement countryIsoCode = countryElement.findChild("short_name");
                            if (countryIsoCode != null) {
                                return countryIsoCode.toContent();
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static String getCurrentCountry(String lat, String lon) {
        UtilityLogger.info("lat: " + lat + "\nLon: " + lon);
        if (lat != null && lon != null) {
            try {
                //get country from google map
                String urlStr = "http://maps.googleapis.com/maps/api/geocode/xml?latlng=" + lat + "," + lon + "&sensor=false";
                URL url = new URL(urlStr);
                URLConnection urlConnection = url.openConnection();
                InputStream inn = urlConnection.getInputStream();
                VDocument doc = VDocument.parseDocument(inn);
                VElement root = doc.getRootElement();
                VElement status = root.findChild("status");
                UtilityLogger.fine(doc.toXmlString());
                if (status != null && "OK".equalsIgnoreCase(status.toContent())) {
                    //good result
                    VElement anyResult = root.findChild("result");
                    if (anyResult != null) {
                        VElement countryElement = anyResult.findChild("address_component", "type", "country");
                        if (countryElement != null) {
                            //find the value
                            VElement countryIsoCode = countryElement.findChild("long_name");
                            if (countryIsoCode != null) {
                                return countryIsoCode.toContent();
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Converts the string to a long value, regardless of its representation. The algorithm simply concatenates the byte values as a long string.
     * <p>
     * @param str
     * @return
     */
    public static Long toLongValue(String str) {
        String strval = "";
        for (byte b : str.getBytes()) {
            strval += b;
        }
        return Long.parseLong(strval);
    }

    public static Object convertFromByteArray(byte[] byteObject) throws IOException,
            ClassNotFoundException {
        ByteArrayInputStream bais;
        ObjectInputStream in;
        bais = new ByteArrayInputStream(byteObject);
        in = new ObjectInputStream(bais);
        Object o = in.readObject();
        in.close();
        return o;
    }

    public static byte[] convertToByteArray(Object complexObject) throws IOException {
        ByteArrayOutputStream baos;
        ObjectOutputStream out;
        baos = new ByteArrayOutputStream();
        out = new ObjectOutputStream(baos);
        out.writeObject(complexObject);
        out.close();
        return baos.toByteArray();
    }

    public static String toString(byte[] arr) {
        String array = "";
        if (arr == null) {
            return null;
        }
        for (int i = 0; i < arr.length; i++) {
            byte t = arr[i];
            if (!array.isEmpty()) {
                array += ",";
            }
            array += t;
        }
        return "[" + array + "]";
    }

    public static byte[] fromArrayString(String array) {
        if (array == null) {
            return null;
        }
        array = array.trim();
        if (array.startsWith("[") && array.endsWith("]")) {
            array = array.substring(1, array.length() - 1);
            String[] str = array.split(",");
            byte[] val = new byte[str.length];
            int i = 0;
            for (String s : str) {
                s = s.trim();
                byte b = 0;
                if (!s.isEmpty() && isNumber(s)) {
                    b = Byte.parseByte(s);
                }
                val[i++] = b;
            }
            return val;
        }
        return null;
    }

    /**
     * Extracts any number from the string. The first whole number that can be determined is returned. Returns empty string if no number is extracted.
     * <p>
     * @param data
     * @return
     */
    public static String extractNumber(String data) {
        String num = "";
        boolean decEncountered = false;
        for (char c : data.toCharArray()) {
            if (Character.isDigit(c)) {
                num += c;
            } else if (c == '.' && !decEncountered) {
                decEncountered = true;
                num += c;
            } else if (c == ' ') {
                continue; //space
            } else if (!num.isEmpty()) {
                return num;
            }
        }
        return num;
    }

    public static <T extends Number> String sumToString(T[] bs) {
        String s = "";
        for (T t : bs) {
            s += t.toString();
        }
        return s;
    }

    public static String sumToString(byte[] bs) {
        String s = "";
        for (byte t : bs) {
            s += t;
        }
        return s;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty() || str.trim().toLowerCase().trim().equals("null");
    }

    public static String cleanXml(String xml) {
        try {
            HtmlCleaner htmlCleaner = new HtmlCleaner();
            CleanerProperties cp = htmlCleaner.getProperties();
            TagNode node = htmlCleaner.clean(xml);
            String cleanXml = new SimpleXmlSerializer(cp).getAsString(node);
            return cleanXml;
        } catch (Exception ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    public static String formatCurrencyValue(BigDecimal value) {
        value = value.setScale(2);
        CurrencyFormatter cf = new CurrencyFormatter(2);
        return cf.format(value);
    }

    public static BigDecimal formatCurrencyValue(String value) {
        value = value.replaceAll(",\\W+", "");
        return new BigDecimal(value);
    }

    public static void main(String[] args) {
        String address = "Outering Road, Donholm";
        GoogleGeocodeResponse ggr = getCurrentLocationFromGoogle(address);
        UtilityLogger.fine(VMarshaller.toXmlString(ggr));
    }

    private static String doCapitalize(String str) {
        str += " "; //we add this space for 1 letter word.
        return (Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase()).trim();
    }

    public static String capitalize(String str) {
        String[] parts = str.split(" "); //split using spaces
        String result = "";
        for (String s : parts) {
            result += (doCapitalize(s) + " ");
        }
        return result.trim();
    }
}
