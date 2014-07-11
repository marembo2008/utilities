/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.geocode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author marembo
 */
public enum Country {

    AFGHANISTAN("AF", "AFG", "Afghanistan", "93"),
    ALBANIA("AL", "ALB", "Albania", "355"),
    ALGERIA("DZ", "DZA", "Algeria", "213"),
    AMERICAN_SAMOA("AS", "ASM", "American Samoa", "1 684"),
    ANDORRA("AD", "AND", "Andorra", "376"),
    ANGOLA("AO", "AGO", "Angola", "244"),
    ANGUILLA("AI", "AIA", "Anguilla", "1 264"),
    ANTARCTICA("AQ", "ATA", "Antarctica", "672"),
    ANTIGUA_AND_BARBUDA("AG", "ATG", "Antigua and Barbuda", "1 268"),
    ARGENTINA("AR", "ARG", "Argentina", "54"),
    ARMENIA("AM", "ARM", "Armenia", "374"),
    ARUBA("AW", "ABW", "Aruba", "297"),
    AUSTRALIA("AU", "AUS", "Australia", "61"),
    AUSTRIA("AT", "AUT", "Austria", "43"),
    AZERBAIJAN("AZ", "AZE", "Azerbaijan", "994"),
    BAHAMAS("BS", "BHS", "Bahamas", "1 242"),
    BAHRAIN("BH", "BHR", "Bahrain", "973"),
    BANGLADESH("BD", "BGD", "Bangladesh", "880"),
    BARBADOS("BB", "BRB", "Barbados", "1 246"),
    BELARUS("BY", "BLR", "Belarus", "375"),
    BELGIUM("BE", "BEL", "Belgium", "32"),
    BELIZE("BZ", "BLZ", "Belize", "501"),
    BENIN("BJ", "BEN", "Benin", "229"),
    BERMUDA("BM", "BMU", "Bermuda", "1 441"),
    BHUTAN("BT", "BTN", "Bhutan", "975"),
    BOLIVIA("BO", "BOL", "Bolivia", "591"),
    BOSNIA_AND_HERZEGOVINA("BA", "BIH", "Bosnia and Herzegovina", "387"),
    BOTSWANA("BW", "BWA", "Botswana", "267"),
    BRAZIL("BR", "BRA", "Brazil", "55"),
    BRITISH_INDIAN_OCEAN_TERRITORY("IO", "IOT", "British Indian Ocean Territory", " "),
    BRITISH_VIRGIN_ISLANDS("VG", "VGB", "British Virgin Islands", "1 284"),
    BRUNEI("BN", "BRN", "Brunei", "673"),
    BULGARIA("BG", "BGR", "Bulgaria", "359"),
    BURKINA_FASO("BF", "BFA", "Burkina Faso", "226"),
    BURMA_MYANMAR_("MM", "MMR", "Burma (Myanmar)", "95"),
    BURUNDI("BI", "BDI", "Burundi", "257"),
    CAMBODIA("KH", "KHM", "Cambodia", "855"),
    CAMEROON("CM", "CMR", "Cameroon", "237"),
    CANADA("CA", "CAN", "Canada", "1"),
    CAPE_VERDE("CV", "CPV", "Cape Verde", "238"),
    CAYMAN_ISLANDS("KY", "CYM", "Cayman Islands", "1 345"),
    CENTRAL_AFRICAN_REPUBLIC("CF", "CAF", "Central African Republic", "236"),
    CHAD("TD", "TCD", "Chad", "235"),
    CHILE("CL", "CHL", "Chile", "56"),
    CHINA("CN", "CHN", "China", "86"),
    CHRISTMAS_ISLAND("CX", "CXR", "Christmas Island", "61"),
    COCOS_KEELING_ISLANDS("CC", "CCK", "Cocos (Keeling) Islands", "61"),
    COLOMBIA("CO", "COL", "Colombia", "57"),
    COMOROS("KM", "COM", "Comoros", "269"),
    COOK_ISLANDS("CK", "COK", "Cook Islands", "682"),
    COSTA_RICA("CR", "CRC", "Costa Rica", "506"),
    CROATIA("HR", "HRV", "Croatia", "385"),
    CUBA("CU", "CUB", "Cuba", "53"),
    CYPRUS("CY", "CYP", "Cyprus", "357"),
    CZECH_REPUBLIC("CZ", "CZE", "Czech Republic", "420"),
    DEMOCRATIC_REPUBLIC_OF_THE_CONGO("CD", "COD", "Democratic Republic of the Congo", "243"),
    DENMARK("DK", "DNK", "Denmark", "45"),
    DJIBOUTI("DJ", "DJI", "Djibouti", "253"),
    DOMINICA("DM", "DMA", "Dominica", "1 767"),
    DOMINICAN_REPUBLIC("DO", "DOM", "Dominican Republic", "1 809"),
    ECUADOR("EC", "ECU", "Ecuador", "593"),
    EGYPT("EG", "EGY", "Egypt", "20"),
    EL_SALVADOR("SV", "SLV", "El Salvador", "503"),
    EQUATORIAL_GUINEA("GQ", "GNQ", "Equatorial Guinea", "240"),
    ERITREA("ER", "ERI", "Eritrea", "291"),
    ESTONIA("EE", "EST", "Estonia", "372"),
    ETHIOPIA("ET", "ETH", "Ethiopia", "251"),
    FALKLAND_ISLANDS("FK", "FLK", "Falkland Islands", "500"),
    FAROE_ISLANDS("FO", "FRO", "Faroe Islands", "298"),
    FIJI("FJ", "FJI", "Fiji", "679"),
    FINLAND("FI", "FIN", "Finland", "358"),
    FRANCE("FR", "FRA", "France", "33"),
    FRENCH_POLYNESIA("PF", "PYF", "French Polynesia", "689"),
    GABON("GA", "GAB", "Gabon", "241"),
    GAMBIA("GM", "GMB", "Gambia", "220"),
    GAZA_STRIP(" ", " ", "Gaza Strip", "970"),
    GEORGIA("GE", "GEO", "Georgia", "995"),
    GERMANY("DE", "DEU", "Germany", "49"),
    GHANA("GH", "GHA", "Ghana", "233"),
    GIBRALTAR("GI", "GIB", "Gibraltar", "350"),
    GREECE("GR", "GRC", "Greece", "30"),
    GREENLAND("GL", "GRL", "Greenland", "299"),
    GRENADA("GD", "GRD", "Grenada", "1 473"),
    GUAM("GU", "GUM", "Guam", "1 671"),
    GUATEMALA("GT", "GTM", "Guatemala", "502"),
    GUINEA("GN", "GIN", "Guinea", "224"),
    GUINEA_BISSAU("GW", "GNB", "Guinea-Bissau", "245"),
    GUYANA("GY", "GUY", "Guyana", "592"),
    HAITI("HT", "HTI", "Haiti", "509"),
    HOLY_SEE_VATICAN_CITY_("VA", "VAT", "Holy See (Vatican City)", "39"),
    HONDURAS("HN", "HND", "Honduras", "504"),
    HONG_KONG("HK", "HKG", "Hong Kong", "852"),
    HUNGARY("HU", "HUN", "Hungary", "36"),
    ICELAND("IS", "IS", "Iceland", "354"),
    INDIA("IN", "IND", "India", "91"),
    INDONESIA("ID", "IDN", "Indonesia", "62"),
    IRAN("IR", "IRN", "Iran", "98"),
    IRAQ("IQ", "IRQ", "Iraq", "964"),
    IRELAND("IE", "IRL", "Ireland", "353"),
    ISLE_OF_MAN("IM", "IMN", "Isle of Man", "44"),
    ISRAEL("IL", "ISR", "Israel", "972"),
    ITALY("IT", "ITA", "Italy", "39"),
    IVORY_COAST("CI", "CIV", "Ivory Coast", "225"),
    JAMAICA("JM", "JAM", "Jamaica", "1 876"),
    JAPAN("JP", "JPN", "Japan", "81"),
    JERSEY("JE", "JEY", "Jersey", " "),
    JORDAN("JO", "JOR", "Jordan", "962"),
    KAZAKHSTAN("KZ", "KAZ", "Kazakhstan", "7"),
    KENYA("KE", "KEN", "Kenya", "254"),
    KIRIBATI("KI", "KIR", "Kiribati", "686"),
    KOSOVO(" ", " ", "Kosovo", "381"),
    KUWAIT("KW", "KWT", "Kuwait", "965"),
    KYRGYZSTAN("KG", "KGZ", "Kyrgyzstan", "996"),
    LAOS("LA", "LAO", "Laos", "856"),
    LATVIA("LV", "LVA", "Latvia", "371"),
    LEBANON("LB", "LBN", "Lebanon", "961"),
    LESOTHO("LS", "LSO", "Lesotho", "266"),
    LIBERIA("LR", "LBR", "Liberia", "231"),
    LIBYA("LY", "LBY", "Libya", "218"),
    LIECHTENSTEIN("LI", "LIE", "Liechtenstein", "423"),
    LITHUANIA("LT", "LTU", "Lithuania", "370"),
    LUXEMBOURG("LU", "LUX", "Luxembourg", "352"),
    MACAU("MO", "MAC", "Macau", "853"),
    MACEDONIA("MK", "MKD", "Macedonia", "389"),
    MADAGASCAR("MG", "MDG", "Madagascar", "261"),
    MALAWI("MW", "MWI", "Malawi", "265"),
    MALAYSIA("MY", "MYS", "Malaysia", "60"),
    MALDIVES("MV", "MDV", "Maldives", "960"),
    MALI("ML", "MLI", "Mali", "223"),
    MALTA("MT", "MLT", "Malta", "356"),
    MARSHALL_ISLANDS("MH", "MHL", "Marshall Islands", "692"),
    MAURITANIA("MR", "MRT", "Mauritania", "222"),
    MAURITIUS("MU", "MUS", "Mauritius", "230"),
    MAYOTTE("YT", "MYT", "Mayotte", "262"),
    MEXICO("MX", "MEX", "Mexico", "52"),
    MICRONESIA("FM", "FSM", "Micronesia", "691"),
    MOLDOVA("MD", "MDA", "Moldova", "373"),
    MONACO("MC", "MCO", "Monaco", "377"),
    MONGOLIA("MN", "MNG", "Mongolia", "976"),
    MONTENEGRO("ME", "MNE", "Montenegro", "382"),
    MONTSERRAT("MS", "MSR", "Montserrat", "1 664"),
    MOROCCO("MA", "MAR", "Morocco", "212"),
    MOZAMBIQUE("MZ", "MOZ", "Mozambique", "258"),
    NAMIBIA("NA", "NAM", "Namibia", "264"),
    NAURU("NR", "NRU", "Nauru", "674"),
    NEPAL("NP", "NPL", "Nepal", "977"),
    NETHERLANDS("NL", "NLD", "Netherlands", "31"),
    NETHERLANDS_ANTILLES("AN", "ANT", "Netherlands Antilles", "599"),
    NEW_CALEDONIA("NC", "NCL", "New Caledonia", "687"),
    NEW_ZEALAND("NZ", "NZL", "New Zealand", "64"),
    NICARAGUA("NI", "NIC", "Nicaragua", "505"),
    NIGER("NE", "NER", "Niger", "227"),
    NIGERIA("NG", "NGA", "Nigeria", "234"),
    NIUE("NU", "NIU", "Niue", "683"),
    NORFOLK_ISLAND(" ", "NFK", "Norfolk Island", "672"),
    NORTH_KOREA("KP", "PRK", "North Korea", "850"),
    NORTHERN_MARIANA_ISLANDS("MP", "MNP", "Northern Mariana Islands", "1 670"),
    NORWAY("NO", "NOR", "Norway", "47"),
    OMAN("OM", "OMN", "Oman", "968"),
    PAKISTAN("PK", "PAK", "Pakistan", "92"),
    PALAU("PW", "PLW", "Palau", "680"),
    PANAMA("PA", "PAN", "Panama", "507"),
    PAPUA_NEW_GUINEA("PG", "PNG", "Papua New Guinea", "675"),
    PARAGUAY("PY", "PRY", "Paraguay", "595"),
    PERU("PE", "PER", "Peru", "51"),
    PHILIPPINES("PH", "PHL", "Philippines", "63"),
    PITCAIRN_ISLANDS("PN", "PCN", "Pitcairn Islands", "870"),
    POLAND("PL", "POL", "Poland", "48"),
    PORTUGAL("PT", "PRT", "Portugal", "351"),
    PUERTO_RICO("PR", "PRI", "Puerto Rico", "1"),
    QATAR("QA", "QAT", "Qatar", "974"),
    REPUBLIC_OF_THE_CONGO("CG", "COG", "Republic of the Congo", "242"),
    ROMANIA("RO", "ROU", "Romania", "40"),
    RUSSIA("RU", "RUS", "Russia", "7"),
    RWANDA("RW", "RWA", "Rwanda", "250"),
    SAINT_BARTHELEMY("BL", "BLM", "Saint Barthelemy", "590"),
    SAINT_HELENA("SH", "SHN", "Saint Helena", "290"),
    SAINT_KITTS_AND_NEVIS("KN", "KNA", "Saint Kitts and Nevis", "1 869"),
    SAINT_LUCIA("LC", "LCA", "Saint Lucia", "1 758"),
    SAINT_MARTIN("MF", "MAF", "Saint Martin", "1 599"),
    SAINT_PIERRE_AND_MIQUELON("PM", "SPM", "Saint Pierre and Miquelon", "508"),
    SAINT_VINCENT_AND_THE_GRENADINES("VC", "VCT", "Saint Vincent and the Grenadines", "1 784"),
    SAMOA("WS", "WSM", "Samoa", "685"),
    SAN_MARINO("SM", "SMR", "San Marino", "378"),
    SAO_TOME_AND_PRINCIPE("ST", "STP", "Sao Tome and Principe", "239"),
    SAUDI_ARABIA("SA", "SAU", "Saudi Arabia", "966"),
    SENEGAL("SN", "SEN", "Senegal", "221"),
    SERBIA("RS", "SRB", "Serbia", "381"),
    SEYCHELLES("SC", "SYC", "Seychelles", "248"),
    SIERRA_LEONE("SL", "SLE", "Sierra Leone", "232"),
    SINGAPORE("SG", "SGP", "Singapore", "65"),
    SLOVAKIA("SK", "SVK", "Slovakia", "421"),
    SLOVENIA("SI", "SVN", "Slovenia", "386"),
    SOLOMON_ISLANDS("SB", "SLB", "Solomon Islands", "677"),
    SOMALIA("SO", "SOM", "Somalia", "252"),
    SOUTH_AFRICA("ZA", "ZAF", "South Africa", "27"),
    SOUTH_KOREA("KR", "KOR", "South Korea", "82"),
    SOUTH_SUDAN("SS", "SSD", "South Sudan", "211"),
    SPAIN("ES", "ESP", "Spain", "34"),
    SRI_LANKA("LK", "LKA", "Sri Lanka", "94"),
    SUDAN("SD", "SDN", "Sudan", "249"),
    SURINAME("SR", "SUR", "Suriname", "597"),
    SVALBARD("SJ", "SJM", "Svalbard", " "),
    SWAZILAND("SZ", "SWZ", "Swaziland", "268"),
    SWEDEN("SE", "SWE", "Sweden", "46"),
    SWITZERLAND("CH", "CHE", "Switzerland", "41"),
    SYRIA("SY", "SYR", "Syria", "963"),
    TAIWAN("TW", "TWN", "Taiwan", "886"),
    TAJIKISTAN("TJ", "TJK", "Tajikistan", "992"),
    TANZANIA("TZ", "TZA", "Tanzania", "255"),
    THAILAND("TH", "THA", "Thailand", "66"),
    TIMOR_LESTE("TL", "TLS", "Timor-Leste", "670"),
    TOGO("TG", "TGO", "Togo", "228"),
    TOKELAU("TK", "TKL", "Tokelau", "690"),
    TONGA("TO", "TON", "Tonga", "676"),
    TRINIDAD_AND_TOBAGO("TT", "TTO", "Trinidad and Tobago", "1 868"),
    TUNISIA("TN", "TUN", "Tunisia", "216"),
    TURKEY("TR", "TUR", "Turkey", "90"),
    TURKMENISTAN("TM", "TKM", "Turkmenistan", "993"),
    TURKS_AND_CAICOS_ISLANDS("TC", "TCA", "Turks and Caicos Islands", "1 649"),
    TUVALU("TV", "TUV", "Tuvalu", "688"),
    UGANDA("UG", "UGA", "Uganda", "256"),
    UKRAINE("UA", "UKR", "Ukraine", "380"),
    UNITED_ARAB_EMIRATES("AE", "ARE", "United Arab Emirates", "971"),
    UNITED_KINGDOM("GB", "GBR", "United Kingdom", "44"),
    UNITED_STATES("US", "USA", "United States", "1"),
    URUGUAY("UY", "URY", "Uruguay", "598"),
    US_VIRGIN_ISLANDS("VI", "VIR", "US Virgin Islands", "1 340"),
    UZBEKISTAN("UZ", "UZB", "Uzbekistan", "998"),
    VANUATU("VU", "VUT", "Vanuatu", "678"),
    VENEZUELA("VE", "VEN", "Venezuela", "58"),
    VIETNAM("VN", "VNM", "Vietnam", "84"),
    WALLIS_AND_FUTUNA("WF", "WLF", "Wallis and Futuna", "681"),
    WEST_BANK(" ", " ", "West Bank", "970"),
    WESTERN_SAHARA("EH", "ESH", "Western Sahara", " "),
    YEMEN("YE", "YEM", "Yemen", "967"),
    ZAMBIA("ZM", "ZMB", "Zambia", "260"),
    ZIMBABWE("ZW", "ZWE", "Zimbabwe", "263");

    private final String isoCode;
    private final String isoCode2;
    private final String name;
    private final String internationalDialingCode;

    private Country(String isoCode, String isoCode2, String name, String internationalDialingCode) {
        this.isoCode = isoCode;
        this.isoCode2 = isoCode2;
        this.name = name;
        this.internationalDialingCode = internationalDialingCode;
    }

    public String getIsoCode() {
        return this.isoCode;
    }

    public String getIsoCode2() {
        return this.isoCode2;
    }

    public String getName() {
        return this.name;
    }

    public String getInternationalDialingCode() {
        return this.internationalDialingCode;
    }

    public static Country fromIsoCode(final String isoCode) {
        for (Country c : values()) {
            if (c.isoCode.equalsIgnoreCase(isoCode)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unsupported country iso-code: " + isoCode);
    }

    public static Country fromIsoCode2(final String isoCode2) {
        for (Country c : values()) {
            if (c.isoCode2.equalsIgnoreCase(isoCode2)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unsupported country iso-code2: " + isoCode2);
    }

    /**
     * Returns sorted sorted, cannot be modified.
     *
     * @return
     */
    public static Set<Country> countries() {
        @SuppressWarnings("SetReplaceableByEnumSet")
        SortedSet<Country> countries = new TreeSet<Country>(new Comparator<Country>() {

            @Override
            public int compare(Country o1, Country o2) {
                return o1.name().compareToIgnoreCase(o2.name());
            }
        });
        countries.addAll(Arrays.asList(values()));
        return Collections.unmodifiableSortedSet(countries);
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Will be removed after all usages have been moved.
     *
     * @return
     */
    public CountryCode toCountryCode() {
        return new CountryCode(name, isoCode, isoCode2, internationalDialingCode);
    }
}
