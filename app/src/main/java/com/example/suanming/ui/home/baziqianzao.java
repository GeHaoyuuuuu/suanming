package com.example.suanming.ui.home;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * 八字算命
 */
public class baziqianzao {

    public String[][] DayZhuMemo; //全局变量，每个日柱对应的乾造

    /**
     *
     * @param Mydate 生日，格式为yyyy-mm-dd
     * @param Mytime 出生时刻，0-23
     * @return 返回乾造
     * @throws ParseException
     */
    public String paipan(String Mydate,String Mytime) throws ParseException {

        Calendar mancal;

        try {
            mancal = getCalendarfromString(Mydate, "yyyy-MM-dd");
        } catch (ParseException ex) {
            return "输入日期格式不正确" + ex.getMessage();
        }
        int hour =  Integer.parseInt(Mytime);
        if(hour>=0&&hour<24) {
            return paipan(mancal,hour) ;
        }
        else
        {
            return "输入出生时刻格式不正确";
        }

    }

    /**
     *
     * @param year 生日
     * @return 返回Calendar格式日期
     * @throws ParseException
     */
    private Calendar getCalendarfromString(String year, String DateFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(year));
        return cal;
    }

    public String paipan(Calendar cal,int hour) throws ParseException {

        BaZi lunar = new BaZi(cal);
        String s1 = "此人农历的日期【" + lunar.toString() + "】\n";
        /**
         * 子时[23.00,1:00),丑时[1.00,3.00),寅时[3.00,5.00},卯时[5.00,7.00),
         * 辰时[7.00,9.00),巳时[9.00,11.00),午时[11.00,13.00),未时[13.00,15.00)
         * 申时[15.00,17.00),酉时[17.00,19.00),戌时[19.00,21.00),亥时[21.00,23.00)
         * 求时辰干支
         */
        if(hour == 23) {
            hour = 1;
        }
        else {
            hour = (hour+3)/2;
        }

        String s2 = "此人八字【" + lunar.getYearGanZhi(hour) + "】\n";
        //获取生肖
        String s3 = "此人的农历生肖【" + lunar.animalsYear() + "】\n";

        String GanZhi = lunar.getYearGanZhi(hour);	//取八字
        String[] tempchar = GanZhi.split(",");
        String ganziyear = tempchar[0];//年柱
        String ganzimonth = tempchar[1];//月柱
        String ganziday = tempchar[2];//日柱
        String ganzitime = tempchar[3];//时柱

        //八字乾造只看日柱 tempchar[2]
        String s = s1+s2+s3+DayText(tempchar[2]);
        return s;
    }

    /**八字乾造
     * @param paramString 日柱
     * @return 日柱对应的命格
     */
    public String DayText(String paramString) {
        String str = "";
        for (int i = 0; i < 60; i++) {

            if (this.DayZhuMemo[i][0].equals(paramString)) {
                str = this.DayZhuMemo[i][1];
                break;
            }

        }
        return str;

    }


    public baziqianzao() {
        String[][] arrayOfString = new String[60][];
        String[] arrayOfString1 = new String[2];
        arrayOfString1[0] = "甲子";
        arrayOfString1[1] = "天德贵人日。坐子、沐浴，逢官临桃花。白玉仙子捧印来，一举成名天门开。贵人不向西方去，烽火空负旷世才。辛为正官，庚为偏官，戊己为财，见甲乙为破财，丙丁伤名利艰难，生在子月，无丑合，离祖自立，亥卯辰月主贵，巳月平常之命，午月甲死子神冲，他乡立业。申月，子嗣难有。辰月，移根换叶。亥月，文章显达。";
        arrayOfString[0] = arrayOfString1;
        String[] arrayOfString2 = new String[2];
        arrayOfString2[0] = "甲寅";
        arrayOfString2[1] = "天禄贵人日。坐比肩，食神，临官禄。禄到人间最为奇，千秋功业酬白帝。田园风光好福气，春江月夜柳丝垂。双木并排，见寅月，孤克，二三妻，见申酉月大贵，卯月，身太旺破财，巳月，犯刑，亥月，早步帝阙，子月拱丑，贵。午月，会东方火局，才华超群，辰月，广置阡陌。";
        arrayOfString[1] = arrayOfString2;
        String[] arrayOfString3 = new String[2];
        arrayOfString3[0] = "甲辰";
        arrayOfString3[1] = "龙守财库日。坐偏财库，临衰。身坐财库一世荣，慷慨风流人多情。财团公司善交际，官星透显管万民。子月，水多木飘，主移根换叶。申月、贵，酉月，富贵双显。午戌月，主富，卯月，羊刃主败财。丑月富厚有财，亥月透官，最贵。寅月，龙虎拱月，叫龙吟虎啸。";
        arrayOfString[2] = arrayOfString3;
        String[] arrayOfString4 = new String[2];
        arrayOfString4[0] = "甲午";
        arrayOfString4[1] = "龙马奔驰日。坐死、伤官、财地、进贵日。龙马交弛好福气，娇妻美女喜北地。八月桂花香千里，春风丽日相依依。子月，冲午，鸳鸯难合。亥卯未月，贵显。午月，自刑，见亥子，主富。丑月，身弱，见火贫。寅月，得申酉吉，火月劳碌贫夭；卯月，财不聚；申酉月，武职得权。";
        arrayOfString[3] = arrayOfString4;
        String[] arrayOfString5 = new String[2];
        arrayOfString5[0] = "甲申";
        arrayOfString5[1] = "龙虎夺魁日。坐绝，临杀化印。跃马横戈驰天涯，秦山楚国帅府家。儿女手足喜相逢，斩将夺关壮士夸。生于子月，印化煞，贵。亥卯未月，都贵，申月申时，死，夭命。丑月，带疾，辰月，孤独或僧道。巳月，清贫，敦厚聪明，且有刑。午月，艺业成名，酉月，先贵有疾，一成一败，亥月，文章显达。";
        arrayOfString[4] = arrayOfString5;
        String[] arrayOfString6 = new String[2];
        arrayOfString6[0] = "甲戌";
        arrayOfString6[1] = "青龙献艺日。坐养，临偏财，伤官。一世荣华走他乡，千般艺技样样强。官星印星来捧上，风流多情歌舞场。子月，书海成名。丑未月，冲刑灾，多病。寅月贵，工作好，对妻不利，丑月，大富，巳月名利两全。午月，富而贵。辰月，僧道清高。亥月，艺人，功名不遂。戌月，背禄逐马，鸡鸭同鸣。甲临戌，财库，富贵双全日。";
        arrayOfString[5] = arrayOfString6;
        String[] arrayOfString7 = new String[2];
        arrayOfString7[0] = "乙丑";
        arrayOfString7[1] = "玉女佩珠日（丁为玉女，丑为珠）。临衰，坐枭与偏财，将星。身坐金库财福秀，衣禄荣华样样有。金水相涵好文章，东方西方对面谈。生于子月，丑合，贵，丑月，带疾；寅月，寅中丙火克庚金官星，不禄。申酉月，无火，寿长。寅卯月败财。辰戌月，富贵。亥子月，诗文扬。巳午月，福寿。戌月，清秀厚道，财富丰盈。";
        arrayOfString[6] = arrayOfString7;
        String[] arrayOfString8 = new String[2];
        arrayOfString8[0] = "乙卯";
        arrayOfString8[1] = "风云相会日。坐禄，临比肩，爵位，天乙贵人。身坐爵位人称羡，功名显达列朝班。苍海珠玉会雨露，青山白云流水远。子月，偏印，文笔命，喜食伤生财，土月富，亥月，辛官，武职勋业。寅卯月，财绝，僧道有缘。巳午月，破财，申酉月显赫。戌月福而寿。未月财富丰盈。";
        arrayOfString[7] = arrayOfString8;
        String[] arrayOfString9 = new String[2];
        arrayOfString9[0] = "乙巳";
        arrayOfString9[1] = "木火生辉日。交贵，驿马，正财，成名。刚愎自用又聪明，财官同见公候命。文才武略怕青龙，兴旺成败一刻中。子月，文才出众，一生劳碌。申月，官得生，近臣。丑月，武职建奇。亥卯月，通根见官杀贵。未月，有福，营商发财。巳午月，妻病或离别。申酉月，带疾，肝胆病。亥月，文章出奇。戌月，入墓，富而寿短。";
        arrayOfString[8] = arrayOfString9;
        String[] arrayOfString10 = new String[2];
        arrayOfString10[0] = "乙未";
        arrayOfString10[1] = "财福日。临财，坐养。也叫福贵目。天元坐福人聪颖，得官逢比赛富翁。丝绸路上愁石榴，春色秋花雨蒙蒙。子月，平常命。未月，孤刑，申酉月，肾病，阳痿，女姓有妇科病，亥月，大贵。辰戌丑月，福商巨贾。午月，声名天下，夏月生，平常，冬生，寿长。巳月，功名难遂，艺业生涯。";
        arrayOfString[9] = arrayOfString10;
        String[] arrayOfString11 = new String[2];
        arrayOfString11[0] = "乙酉";
        arrayOfString11[1] = "龙凤呈祥日。（生于酉月为贵为蒙难日）坐绝，将星。春花江水落凤霞，南北扬名匡天下。南方一去坐金殿，玉石翡翠泪花花。子月，生身，逢财星，吉。寅卯月，显贵。午月，艺名生涯。申月，贫命，劳碌。酉月，自刑，伤禄破命目疾，主灾，行印运时吉。亥月，喜财星，田园丰盛，巳月败散祖业，乙酉，多伤残。";
        arrayOfString[10] = arrayOfString11;
        String[] arrayOfString12 = new String[2];
        arrayOfString12[0] = "乙亥";
        arrayOfString12[1] = "名利双成日。坐死，临正印，劫财，文星贵人。玉兔月桂喜官星，亲姻朋辈重友情。青竹流水郁葱葱，太阳投江重复行。子月，主有富，喜官杀显贵。亥月，自刑。午月，贵命，千荷夏日鲜。寅卯月，财星透，富寿。申月，得官。巳月，天涯风尘。戌月，艺道成名，干戈阵前之命。酉月，伤官，贵，死于非命。";
        arrayOfString[11] = arrayOfString12;
        String[] arrayOfString13 = new String[2];
        arrayOfString13[0] = "丙子";
        arrayOfString13[1] = "漓江照彩日。临胎，正官，喻文曲星，天官贵人。彩照山川凤呈祥，年少成名坐华堂。日落江河人堪伤，东彩西虹任君想。子月，逢印，贵。土月，企业财团。寅卯月，学业有成。午月，贫，自立家业，兄弟难依。申酉月，经济有方。亥月，有疾，夭。子月，不禄，心脏疾患。丑月，透财贵。巳月，刑灾，大肠患疾。辰月，官星暗藏，超群出众。";
        arrayOfString[12] = arrayOfString13;
        String[] arrayOfString14 = new String[2];
        arrayOfString14[0] = "丙寅";
        arrayOfString14[1] = "红日东升日，坐长生，枭印，食神。山川秀丽柳丝青，人生最喜烟霞景。莫向离情虎山行，西南一去幽幽命。酉月，正财，透财贵显。寅月，贵而不长久。卯，见财星福，官星显贵。申月，财官双显。未月，主福。戌月，衣禄平常。亥子月，六品之贵，巳午月，肠胃或肺疾。";
        arrayOfString[13] = arrayOfString14;
        String[] arrayOfString15 = new String[2];
        arrayOfString15[0] = "丙辰";
        arrayOfString15[1] = "火照龙潭日。临官，食神旺，正印。日坐福神受皇恩，高官厚禄子孙兴。平生享尽人间福，女命穿金又戴银。戌月，冲，财门开，富命。辰月，僧道或孤。寅卯月，贵。丑未月，富厚。亥子月，贵格。申酉月，行火运，官至二三品。午月，两三妻，福禄两全。";
        arrayOfString[14] = arrayOfString15;
        String[] arrayOfString16 = new String[2];
        arrayOfString16[0] = "丙午";
        arrayOfString16[1] = "天河落彩日。坐劫地，伤官，羊刃。人逢帝座爵士身，功名仕途显达人。苍龙水火多有厄，细雨蒙蒙入燕门。巳午月，平常命。亥子月，武官，功名挫蹬。午月，贫，倒冲子贵。寅月，三合局，文上显赫。卯月，喜行财星，贵而富。戌月合伙，商人。辰月，实业主。申酉月，财富商贾。";
        arrayOfString[15] = arrayOfString16;
        String[] arrayOfString17 = new String[2];
        arrayOfString17[0] = "丙申";
        arrayOfString17[1] = "火照金城日。病地，偏财，临杀。身临财官显声名，且防比刃杀伤临。马逢帝旺临官处，堆金积玉立大功。子丑月，血疾，申月，文章有名。酉月，妻妾有情。子辰月，带疾，亥月，喜财。午月，一生吉庆无病，长寿。寅卯月，官荣身。未月，虽富肠胃有病。丙申日，遇岁运刑冲，必生灾祸。";
        arrayOfString[16] = arrayOfString17;
        String[] arrayOfString18 = new String[2];
        arrayOfString18[0] = "丙戌";
        arrayOfString18[1] = "天厨贵人日，坐墓，福神财地。玉堂厚禄寒门出，金银珠宝西方路。日落深潭闯鬼门，金榜题名显双亲。生于子月，福寿延，有名有利，地支巳午戌月，富贵双全。申酉月，大富，财团。辰月冲，少年名显。亥月，武职，六品。丑月，一般经济人。辰月，身旺得职，身弱贫贱。";
        arrayOfString[17] = arrayOfString18;
        String[] arrayOfString19 = new String[2];
        arrayOfString19[0] = "丁丑";
        arrayOfString19[1] = "玉女守库日，坐墓，临财。一轮满月彩画鲜，金银满库禄高迁。丽人不行东南地，洁肤玉身受熬煎。丑月，财透干，富。寅卯月，印，学士命。巳午月，妻迟，二婚，破财，一生辛劳。申酉月，金银满贯，戌月刑灾，财去财散两空空。亥月，喜行南方火运，名利有望。子月见火，戎马空忽。";
        arrayOfString[18] = arrayOfString19;
        String[] arrayOfString20 = new String[2];
        arrayOfString20[0] = "丁卯";
        arrayOfString20[1] = "月照蟾宫日。临偏印，坐病。（虚名虚利）日坐偏印身自强，西风不吹日惆怅。驿马交驰到财乡，山斗文章盖一方。寅卯月，印绶，喜行官运，寒门将相。辰戌丑未月、孤星，妻不顺，财不聚。巳午月，夫妻缘份薄，艰苦。午月多婚，贫，夭，申酉月，名利双贵。子月，武职。亥月，文职显耀。";
        arrayOfString[19] = arrayOfString20;
        String[] arrayOfString21 = new String[2];
        arrayOfString21[0] = "丁巳";
        arrayOfString21[1] = "朱雀跃辉日。坐帝旺，劫财，伤官，正财。谢女才高满词馆，等闲平步出少年。旌旗蔽日入凤阕，火焰马疲怨高山。午月，长生，文章显奇。巳月，禄贵。寅卯月，透官星，一品大贵。辰月，商海有名。未月，贵格，申酉月富命。戌月，无福，见癸水，伤目近视。亥月，常常外出。";
        arrayOfString[20] = arrayOfString21;
        String[] arrayOfString22 = new String[2];
        arrayOfString22[0] = "丁未";
        arrayOfString22[1] = "人立画桥日，坐冠带，食神，比肩，偏印。食神生旺胜财官，天河画桥拜金殿。巽风相伴云雨水，太阳夺辉苦贫寒。子月，沙场立功。丑月，外出经商，妻多离别。寅卯月，金堂玉马。巳午月，破祖业，自立家门。申月，财官双美，酉月，大富。辰月，杂气官旺。亥月，将相。丁未，性强，人贵，凶险多。";
        arrayOfString[21] = arrayOfString22;
        String[] arrayOfString23 = new String[2];
        arrayOfString23[0] = "丁酉";
        arrayOfString23[1] = "玉女乘凤日。坐长生，临偏财。朱雀乘凤显英豪，金车玉凤福寿高。贵人龙马东方起，太阳升时漫徒劳。亥月，贵人捧印，酉戌月，犯刑，骨肉无情，因财分张。子月，杀旺，喜行土运，午月，干强，财旺，未月，衣禄平常，申月，财多身弱，富室贫人，戌月，技术生涯。";
        arrayOfString[22] = arrayOfString23;
        String[] arrayOfString24 = new String[2];
        arrayOfString24[0] = "丁亥";
        arrayOfString24[1] = "月照天门日。坐胎，临正印，正官。词馆文章早荣身，驿马七杀风尘人。最喜荷花并蒂开，金水文章佐朝君。亥月，贵且富。子月，行木运，金戈铁马。戌月，冲，技艺精湛。寅卯月，贵而显耀。巳午月，自刑，小商。申酉月，利路绵绵。子月，带疾。辰月，专业技术成名。";
        arrayOfString[23] = arrayOfString24;
        String[] arrayOfString25 = new String[2];
        arrayOfString25[0] = "戊子";
        arrayOfString25[1] = "山环水抱日，临胎，坐正财。水绕山环明月光，烟花影中福高享。勿贪关城槐山梦，江海浮云一空束。子月，喜行火运，福。丑月，聪明，主富贵。寅卯月，弱，病或夭亡，喜火土。巳午月，巳禄，印，午刃，喜行食伤富贵。申月，食旺，贵。酉月，伤名望。土月得才，富贵。亥月，虚秀，财帛不聚。";
        arrayOfString[24] = arrayOfString25;
        String[] arrayOfString26 = new String[2];
        arrayOfString26[0] = "戊寅";
        arrayOfString26[1] = "虎啸山谷日，临长生，坐杀，偏印。将星入命立武功，猛虎纵风显英雄。印绶财官悬天门，南征北战旅马行。卯月，寅月，鬼旺，多疾或夭。巳、午月，印，诗文会海，兵权万里。申酉月，不禄，伤功名，土月，富。亥月，子月，商贾大富。";
        arrayOfString[25] = arrayOfString26;
        String[] arrayOfString27 = new String[2];
        arrayOfString27[0] = "戊辰";
        arrayOfString27[1] = "苍龙出海日。临冠带，正才，比肩，正官。月洒高山江山秀，平生最喜东南游。一生辛勤贵不显，为人热心福气厚。子月，财旺，目盲，无火，虚而不实。丑月，财少，人聪明。寅卯月，官星，身荣。辰月，财不聚，孤克。巳午月，学业二次成名。申酉月，艺名四方。戌月，冲，少年出众。亥月，多疾，子月，无根，漂荡，技艺超群。";
        arrayOfString[26] = arrayOfString27;
        String[] arrayOfString28 = new String[2];
        arrayOfString28[0] = "戊午";
        arrayOfString28[1] = "马奔午门日。临羊刃，正印。日月分秀福气隆，杀官相见主武功。平川一去前程远，戎马西洲比陶公。子月，名利双收，丑月，财旺。寅卯月透干，朝野重臣。午未月印，锦绣文字，透官显贵。申酉月，企业财团董事。戌月财少，平常，孤克。子月，外乡立实。祖业无靠，六亲冷落，亥月，大富，刃旺，性强，人虽贵，凶险多。";
        arrayOfString[27] = arrayOfString28;
        String[] arrayOfString29 = new String[2];
        arrayOfString29[0] = "戊申";
        arrayOfString29[1] = "霞落花簇日。临病，食神，偏财。日坐福星声名显，万卷诗书朝天关。骑驴走马炉中火，风云雷雨步金殿。寅月，冲禄，财旺。子月，财旺，印旺，贵，丑月福，爱酒色，固执。卯月，合食，名利双显。辰戌未月，土气专旺，不聚财，肾病。巳午月，事业沉浮不定多变动。申酉月，专业致富。亥子月，大富。";
        arrayOfString[28] = arrayOfString29;
        String[] arrayOfString30 = new String[2];
        arrayOfString30[0] = "戊戌";
        arrayOfString30[1] = "溪绕画亭日，坐墓，临正印，比劫。热情憨厚心似海，白帝玉女捧印来。溪绕画亭芳香名，田园平川云天外。子月，显贵聚财。丑未戌月，刑灾，有破。寅卯月合印，诗文成章。申酉月，堆金积玉。亥月，冲，心神不定，异地创业。";
        arrayOfString[29] = arrayOfString30;
        String[] arrayOfString31 = new String[2];
        arrayOfString31[0] = "己丑";
        arrayOfString31[1] = "金牛拜金殿。临墓，坐比肩，食神，偏财。一柱佛香拜金殿，艮山流水芳名显。金匙开得丑戈库，富贵荣华醉管弦。亥月，伤官尽，贵，有权威。寅月，贵显。卯月，兵权显赫。申月，庚为背禄。甲绝未月，冲，发迹，肾病。午月，冲，妻有厄。巳月合金，商贾巨富。辰月，孤身。子月，仓库充盈。";
        arrayOfString[30] = arrayOfString31;
        String[] arrayOfString32 = new String[2];
        arrayOfString32[0] = "己卯";
        arrayOfString32[1] = "武跨将坛日，临衰，坐七杀将星。将士佩弓跨战马，暮雨风月渡年华。文星福禄若有情，北国回首似到家。酉月：卯酉冲，一生多迁移，妻离。申月，早发迹。亥月，贵。未月，合，五谷丰登。午月：诗满乾坤。巳月，文秀。辰月，能建功立业。子月，无礼，凶暴。";
        arrayOfString[31] = arrayOfString32;
        String[] arrayOfString33 = new String[2];
        arrayOfString33[0] = "己巳";
        arrayOfString33[1] = "马跃平川日，临帝旺，坐偏印，比劫。南朝天子绶玉印，千里长江醉游人。雪山草地马难行，春风得意座上宾。巳为印，旺，巳月，金神，忌财，喜食伤。午月，显贵。土月，候伯命。申酉月，喜印运，伤尽为武职。亥月，一品贵，有兵权。子月，食伤运大富，辰月，先贫后发。己巳日，人贵。";
        arrayOfString[32] = arrayOfString33;
        String[] arrayOfString34 = new String[2];
        arrayOfString34[0] = "己未";
        arrayOfString34[1] = "丹桂漂香日，临冠带，坐比肩，枭印。月中桂子秋飘香，江河日月交相映，莫道高山芳气散，二月春风论短长。亥月，文章夸跃，清高，酉月，大贵。辰月，小职，近卫。申月，财福充盈，未月，财金散失，午月，合，清贫儒雅。巳月，喜官显贵。辰月，寒门将士。";
        arrayOfString[33] = arrayOfString34;
        String[] arrayOfString35 = new String[2];
        arrayOfString35[0] = "己酉";
        arrayOfString35[1] = "凤飞绿洲日。临长生，坐支食神。一轮满月出苍海，金凤展翅飞天外，秦山昆仑雪皑皑，龙凤呈祥玉珠来。亥月，身弱，贫，寅卯月，有火，武职，酉月，高贵之命，酉多游方术士。申月，无官显贵。未月，大富。巳月，富贵陶朱。子月，食破，贫寒。";
        arrayOfString[34] = arrayOfString35;
        String[] arrayOfString36 = new String[2];
        arrayOfString36[0] = "己亥";
        arrayOfString36[1] = "平川流水日。临胎，坐支正财，正官。禄马同乡拜玉堂，天堑通途文星扬。沉影不随流水去，杀星冲动马无疆。亥月，财显，官旺，贵。酉月，食神，财旺，申月，干透印，大贵。未月合武职。已月，冲，外迹发愤。子月，多病，血疾。寅卯月，支中鬼旺，一生难成大事。印透大贵。";
        arrayOfString[35] = arrayOfString36;
        String[] arrayOfString37 = new String[2];
        arrayOfString37[0] = "庚子";
        arrayOfString37[1] = "金玉出海日，临死，坐支，伤官。能歌善舞笔和墨，犹如白虎戏江水。冲在禄马登科甲，斑竹细雨伤情泪。子月，衰，伤官，无土运，鬼旺，风烛夭贱。丑月，虚名，轻财。寅月，偏财，不禄。卯月，合财，金玉满目。辰月，利路经商。巳月，武职显跃。午月，文官近卫。四季月，印旺富而有名，亥月，漂蓬，僧侣。";
        arrayOfString[36] = arrayOfString37;
        String[] arrayOfString38 = new String[2];
        arrayOfString38[0] = "庚寅";
        arrayOfString38[1] = "白虎镇山日，临绝，坐支偏财，七杀，偏印。平川猛虎归山林，秋风落叶时不宁。最喜大雪封山时，三夏浓荫卧孔明。子月，食旺，身衰，比劫扶吉。寅月，清秀，命高。卯月，富不长久。辰月，富而贵。巳月，鬼暗藏，有印，职荣。申酉月，钱财聚散浮沉，戌亥月，董事财团。";
        arrayOfString[37] = arrayOfString38;
        String[] arrayOfString39 = new String[2];
        arrayOfString39[0] = "庚辰";
        arrayOfString39[1] = "福德贵人日，临养，坐支偏印，食神。命带魁罡性刚强，不信鬼神在身旁。玉佩娇阳入命来，执戈跨马佐高皇。丑月，富而有名。子丑，文才出众。寅卯月，财福寿促，午月，发迹有疾患。巳月，一生艰辛，未申月，财运发迹。酉月合，透官星，荣显。亥子月，食神旺发迹难寿。庚辰，贵而风流，名重利轻。";
        arrayOfString[38] = arrayOfString39;
        String[] arrayOfString40 = new String[2];
        arrayOfString40[0] = "庚午";
        arrayOfString40[1] = "火铸金印日，临沐浴，支坐正官印。铁笔一只水为墨，淡彩浓云笔下绘。学苑将士两般命，山野朱雀衔玉翠。丑月有名声，辰月，自刑，富而有刑。寅月，火旺，带残疾，肺有疾患。卯月，财旺，大富之人。子月冲，天涯艺海。申酉月，日贵，垂手青云。";
        arrayOfString[39] = arrayOfString40;
        String[] arrayOfString41 = new String[2];
        arrayOfString41[0] = "庚申";
        arrayOfString41[1] = "双虎奔驰日。临官禄，坐支比肩，食神，偏印，又叫虎恋玉女日。白虎交驰向南行，雀跃江河早成名。禄到长生官得地，九重露雨沐朱衣。庚月，透火，大贵之命。亥子月，诗词清畅流韵。申酉月，无官星。贫而贱。寅卯月，财满三峡。已午月，官至侍郎，七杀，金戈铁马。";
        arrayOfString[40] = arrayOfString41;
        String[] arrayOfString42 = new String[2];
        arrayOfString42[0] = "庚戌";
        arrayOfString42[1] = "禄马贵人日，临衰，坐支正官，偏印。将军百战不论功，高山流水又出征。西去阳关知音少，前禄后福两三重。辰月，冲，平常之命。卯月，合，因妻发福。寅月，候王之命。丑月，财旺官升。巳月，火官，武职操权，有惊险。午月文职。难善终。申酉月，财来财散，散聚两依依。亥子月，文笔超群。";
        arrayOfString[41] = arrayOfString42;
        String[] arrayOfString43 = new String[2];
        arrayOfString43[0] = "辛丑";
        arrayOfString43[1] = "白玉生辉日，临养，支坐偏印，食神，比肩。白玉生辉金门客，高山得贵子为墨。身入平川多愁叹，干弋影里勋业垂。子月，食神，荣华。丑月，伏吟，鸳鸯难合。寅卯月，财聚官旺。巳月，早遂名香，辰月显达，有名利。午月，凶。申酉月，逢官星，贵，少年坎坷。土月，平常。亥月伤官，一文鸣天下。";
        arrayOfString[42] = arrayOfString43;
        String[] arrayOfString44 = new String[2];
        arrayOfString44[0] = "辛卯";
        arrayOfString44[1] = "凤阙早步日，临绝，支坐财，伤官，驿马，冲禄。高山起程水流长，边塞迢迢雪满霜。佛山玉女岂有情，雪山日照花海棠。辰月，伤官伤尽，自立自成，技艺，卜相，医生。寅卯月，合，财丰。巳月，冲，文星出众。午月，自刑，先荣后刑。未月，富。申月，贫，人生不定。酉月，多争论。子月，食旺，福旺。亥月，伤官。技业成名。辛卯，偏财，为福贵双全日。";
        arrayOfString[43] = arrayOfString44;
        String[] arrayOfString45 = new String[2];
        arrayOfString45[0] = "辛巳";
        arrayOfString45[1] = "金马登殿日，临死，坐支正官，正印，劫财，驿马。金马临官号嘶风，玉堂拜相翰苑名。最喜高山水环绕，娇阳日出漫消魂。子月，食旺，名显。丑月，合，妻少缘，财淡。寅月，因财有刑。卯月，横财。巳月，金长生，化水名显。午月，暗鬼有疾。申酉月，贵中有失。亥月，冲，双贵。";
        arrayOfString[44] = arrayOfString45;
        String[] arrayOfString46 = new String[2];
        arrayOfString46[0] = "辛未";
        arrayOfString46[1] = "冰河解冻日，临衰，支坐枭印，偏官，多情忘义。身入西国佛香地，漫歌轻舞管弦醉。玉女传送风流人，高山日出彩画新。申月，在贵人门下得富。酉月，又贵又富。辰月，库印，冲，清雅儒士。巳月，文月，武操重权，子月，富门贵显。丑月，经济商，云游。亥月，双贵。";
        arrayOfString[45] = arrayOfString46;
        String[] arrayOfString47 = new String[2];
        arrayOfString47[0] = "辛酉";
        arrayOfString47[1] = "凤卧金山日，临禄，支坐比肩，天乙贵人。禄马贵人世少有，凤卧金山将帅候。日出朝阳横天行，月圆金门寻石榴。申月，劫财，一生财不聚。酉月，比肩，财逢劫。子月，福寿名高，巳月，损妻。寅卯月，财气通门户。巳月，合，名扬四海。午月干弋剑影，未戌月，清贫，亥月，富而有刑。";
        arrayOfString[46] = arrayOfString47;
        String[] arrayOfString48 = new String[2];
        arrayOfString48[0] = "辛亥";
        arrayOfString48[1] = "虎行天门日。临沐浴，支坐伤官，正财，驿马。一去天门遥遥远，长亭驿路关山寒。倒骑毛驴东行去，高山丽日花团团。申月，发福。带疾。酉月。破禄，多磨。寅月，富商。卯月财团。巳月，冲，天涯游客。午月，武功建奇。亥月，误入商海。";
        arrayOfString[47] = arrayOfString48;
        String[] arrayOfString49 = new String[2];
        arrayOfString49[0] = "壬子";
        arrayOfString49[1] = "马奔天河日，临帝旺。支坐劫财。壬水浩浩漫天下，行入东方福到家。江南平川鱼米乡，云雨湖海镜中花。子月，平常人。丑月，官星，人清秀。寅月，大富贵。卯月，刑，妻离。辰月，官库，无冲不发。巳月，午月，利路经商。申酉月，文章璀灿。戌月，权重。亥月，贫。";
        arrayOfString[48] = arrayOfString49;
        String[] arrayOfString50 = new String[2];
        arrayOfString50[0] = "壬寅";
        arrayOfString50[1] = "福禄日，临病，支坐食神，偏财偏官。虎跃天河威名扬，犹如箕豹出山岗。禄到长生官得地，九重雨露沐朱衣。卯月，破财，有成有败。土月，吉。午月，正官，荣华显贵。申月，冲身弧，奔波人。酉月，风流才子。戌月，财旺。亥月，财有根富命。子月，因财有破。壬见寅为食神，号富贵双全日。";
        arrayOfString[49] = arrayOfString50;
        String[] arrayOfString51 = new String[2];
        arrayOfString51[0] = "壬辰";
        arrayOfString51[1] = "山流水长日，临墓，支坐偏官，劫财，库地，虚名虚利。江水流芳美如画，杜鹃啼血巫山峡。月下骑马走平川，一日尝尽牡丹花。子月，成中有败，多凶。丑月辰戌未月，俱贵。寅月，食旺，人骑龙背，名流，财旺。卯月，清雅人。巳、午月，广置庄园。申月，奔劳，走乡串野。酉月，合，文上有名，才子。亥月，主掌权。";
        arrayOfString[50] = arrayOfString51;
        String[] arrayOfString52 = new String[2];
        arrayOfString52[0] = "壬午";
        arrayOfString52[1] = "花红柳绿日，临胎，支坐财，正官，驿马。禄马相邀入帝乡，花红柳绿掩高堂。出阕最喜函玉关，千里迢迢雁北上。子月，月日冲，外乡立家，难团园。丑月，禄旺，贵。寅月，贵而多疾。卯月，富贵双显，巳月，财旺，喜印地，午月自刑，夭疾，比劫扶，吉。未月，大富。申、酉月，状元及第。土旺四季，主权。亥月，身旺，财旺。";
        arrayOfString[51] = arrayOfString52;
        String[] arrayOfString53 = new String[2];
        arrayOfString53[0] = "壬申";
        arrayOfString53[1] = "白虎渡江日。临长生。支坐偏印，偏官，劫财，驿马。命似白虎渡长江，最怕风雨江水涨。丽日跨入平川地，金枝玉叶陪身旁。子月，劫财，合水，一生奔波，贫。酉月，偏旺，带疾，弧身。亥月，刑，破财，见官星，大富。丑月，官星，行财运，清秀，禄贵。寅、卯月，食旺，富贵双全。巳、午月，身坐学堂，名利驰驱。申、酉月财帛进退。土月，贵。";
        arrayOfString[52] = arrayOfString53;
        String[] arrayOfString54 = new String[2];
        arrayOfString54[0] = "壬戌";
        arrayOfString54[1] = "龙出苍海日，临冠带，支坐正财，偏官，正印，火库。身坐火库水得福，西行东邀人间苦。壮士难酬青云志，醉看少女漫歌舞。子月，有疾。丑月，人贵显。申月，枭印，劫财，亲姻难全。酉月，印绶，文印齐来。寅月，合，滋生荣茂；卯月，伤名，异路乘凤。巳月，英豪透发，午月，贵显双亲。辰月，青龙飞跃。亥月，兰蕙不禄。";
        arrayOfString[53] = arrayOfString54;
        String[] arrayOfString55 = new String[2];
        arrayOfString55[0] = "癸丑";
        arrayOfString55[1] = "桑柳成荫日，临冠带，支坐偏官，印，比肩。池塘桑柳满园色，二月春风柳絮飞。莫怨高山运来迟，干戈影里是翡翠。午月，冲，财旺，福。未月，主贵。子月，合，功名显达。丑月异常出仕。寅卯月，伤食旺，艺海生涯。巳午月利路经商。四季土月，平平，残疾。申酉月，科场功名，亥子月，决战千里。";
        arrayOfString[54] = arrayOfString55;
        String[] arrayOfString56 = new String[2];
        arrayOfString56[0] = "癸卯";
        arrayOfString56[1] = "天姿文秀日，临长生，支坐食神。学堂词馆贵人命，天姿文秀人多情。最喜三星相拱照，诗琴歌乐官弦声。子月，刑，无礼德，对妻不利，但平步青云。丑月，贵，奇显。寅月，艰难人生。卯月，人生富庶。辰月，财帛富月，父母难靠。巳月富而有残，午月，一生财丰。申月，书香早遂，酉月刀笔成名，冲，鸳鸯离合。土月主贵，亥月，名利双全。";
        arrayOfString[55] = arrayOfString56;
        String[] arrayOfString57 = new String[2];
        arrayOfString57[0] = "癸巳";
        arrayOfString57[1] = "彩霞佩玉日。临胎，支坐正财正官，驿马，文星。贵人玉堂来拜相，墨池泉涌好文章。武士跨马走天下，王公皇候似平常。巳月，财官双美，诗书琴画。午月，中年大富。申酉月，终生劳累，透官。丑月，秀气。辰月，山明水秀，红粉生涯。";
        arrayOfString[56] = arrayOfString57;
        String[] arrayOfString58 = new String[2];
        arrayOfString58[0] = "癸未";
        arrayOfString58[1] = "贵人佩玉日。临墓，支坐偏官，偏财。日临官库将相命，男子英勇女贵荣。西方一去福禄地，花园翠亭马不行。子月，青云得路，贵。丑月，暗鬼冲伤。婚有变。寅月，秀贵，一生顺利。卯月，武职平常。辰月，富而秀贵，巳月，富而且有肺疾。未月，男女无子女，阳痿。申酉月，文字生发。亥月，财发。";
        arrayOfString[57] = arrayOfString58;
        String[] arrayOfString59 = new String[2];
        arrayOfString59[0] = "癸酉";
        arrayOfString59[1] = "天福日，临病，支坐偏印。潇洒功名起一方，一冲一合异寻常。江湖花洒安享福。南去高山势莫档。亥月，生涯遂心，风流。戌月，平常，善智谋。酉月，得祖业，破财。申月，印旺，文上出仕。寅月，艺技生涯。卯月，冲，印破，大富。巳月，富商。午月，生意人。申酉月，金水相涵，文秀。子月，印破，不禄，平常命。";
        arrayOfString[58] = arrayOfString59;
        String[] arrayOfString60 = new String[2];
        arrayOfString60[0] = "癸亥";
        arrayOfString60[1] = "天门悬彩日，临帝旺。支坐伤官。劫财，驿马。九华山上天门开，日行东方花似海。若去西方昆仑地，边塞将士恋故国。未月，合木，贵。在外终。子月，妻离异，财分张；亥月，劫财，一生无正业，散业。四季土月，有作为，决战沙场。寅卯月，财团经营，或艺名天涯。巳午月，大富。癸亥，命薄，多贫贱。";
        arrayOfString[59] = arrayOfString60;
        this.DayZhuMemo = arrayOfString;

    }

}