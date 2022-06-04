package com.example.bookmall.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;


import com.example.bookmall.models.Book;
import com.example.bookmall.utils.ImageBase64;

import java.util.ArrayList;
import java.util.List;

public class BookMapper extends MyDataBaseHelper{
    private static final String TABLE_NAME = "book";
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " +TABLE_NAME+
                    "(id INTEGER PRIMARY KEY, " +
                    "name TEXT," +
                    "coverPic TEXT," +
                    "price REAL," +
                    "author TEXT," +
                    "isbn TEXT," +
                    "description TEXT," +
                    "category_id INTEGER," +
                    "CONSTRAINT fk_category_id FOREIGN KEY(category_id) REFERENCES category(id));";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS "+ TABLE_NAME;

    public BookMapper(@Nullable Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        super.onCreate(sqLiteDatabase);
        List<Book> books = new ArrayList<>();
        books.add(new Book(11,
                "小学教材全解",
                ImageBase64.imageToBase64(this.context,"jiaoyu_q.jpg"),
                40.8F,
                "薛金星",
                "978-7545064971",
                "解题方法全。50多种解题方法，100多种解题技巧在书中多角度解读。真正做到“一册在手，内容方法全有。",
                "教育"));
        books.add(new Book(2,
                "新概念英语",
                ImageBase64.imageToBase64(this.context,"jiaoyu_x.jpg"),
                329.7F,
                "L.G.Alexander",
                "978-7560000008",
                "《新概念英语》（新版）是风靡全世界的英语经典教材，深受广大英语学习者的喜爱和推崇。《新概念英语同步练习册》系列丛书紧贴《新概念英语》课文内容。该书从词汇、句法、语法、阅读、翻译及写作等多方面对学习者进行同步训练，帮助学习者获得扎实基本功，提高读、写、译能力，从而获得良好的语言运用能力和驾驭能力。\n",
                "教育"));
        books.add(new Book(3,
                "俗世奇人",
                ImageBase64.imageToBase64(this.context,"wenyi_su.jpg"),
                58F,
                "冯骥才",
                "978-7506387439",
                "俗世奇人》(全二册)收录了冯骥才先生描写清末民初天津卫奇人异士的所有作品。配有同一时代《醒俗画报》刊载的数十幅图画，其内容大至时政要事，小到市井信息，生活气息浓郁，有助读者感受与认知那个时代。",
                "艺术"));
        books.add(new Book(4,
                "房思琪的初恋乐园",
                ImageBase64.imageToBase64(this.context,"wenyi_f.jpg"),
                40F,
                "林奕含",
                "978-7559614636",
                "令人心碎却无能为力的真实故事。向死而生的文学绝唱。 痛苦的际遇是如此难以分享，好险这个世界还有文学。 我下楼拿作文给李老师改。他掏出来，我被逼到涂在墙上。老师说了九个字：“不行的话，嘴巴可以吧。”我说了五个字：“不行，我不会。”他就塞进来。那感觉像溺水。可以说话之后，我对老师说：“对不起。”有一种功课做不好的感觉。 小小的房思琪住在金碧辉煌的人生里，她的脸和她可以想象的将来一样漂亮。补习班语文名师李国华是同一栋高级住宅的邻居。崇拜文学的小房思琪同样崇拜饱读诗书的李老师。 有一天李老师说，你的程度这么好，不如每个礼拜交一篇作文给我吧，不收你周点费。思琪听话地下楼了。老师在家里等她，桌上没有纸笔。 思琪的初恋是李老师。因为李老师把她翻面，把他的东西塞进去。那年的教师节思琪才十三岁，这个世界和她原本认识的不一样。 如果这是爱情，为什么觉得暴力？为什么觉得被折断？为什么老师要一个女学生换过一个女学生？如果这不是爱情，那满口学问的李老师怎么能做了以后，还这么自信、无疑、无愧于心？ 故事必须重新讲过，与房思琪情同双胞的刘怡婷，接到警局通知，去带回神志不清，被判定疯了的房思琪。透过思琪的日记，怡婷得知思琪五年中的所见所思…… 嫁入钱家的许伊纹，是两位少女的忘年交，二十余岁的她，是两位少女的文学启蒙者同时也是丈夫家暴的沉默受害者…… 升入大学后的郭晓奇仍旧爱着高中时的补习教师李国华，而这位文质彬彬的补习教师并不只有平时人们眼中受人尊敬的老师形象的一面…… 这是一部惊人而特别的小说，小说作者既具有高度敏锐的感受力、又是一个近距离目击者，使这整件事像一个“幸存的标本”那样地被保留下来。整本书反覆地、用极度贴近被侵害者的视角，直直逼视那种“别人夺去你某个珍贵之物”的痛苦──且掠夺之人是以此为乐。",
                "艺术"));
        books.add(new Book(5,
                "Python编程从入门到实践",
                ImageBase64.imageToBase64(this.context,"keji_p.jpg"),
                101F,
                "Eric Metthes",
                "978-7115459190",
                "本书是一本针对所有层次的Python读者而作的Python入门书。全书分两部分：首部分介绍用Python 编程所必须了解的基本概念，包括matplotlib、NumPy和Pygal等强大的Python库和工具介绍，以及列表、字典、if语句、类、文件与异常、代码测试等内容；第二部分将理论付诸实践，讲解如何开发三个项目，包括简单的Python 2D游戏开发，如何利用数据生成交互式的信息图，以及创建和定制简单的Web应用，并帮读者解决常见编程问题和困惑。",
                "科技"));
        books.add(new Book(6,
                "霍金三部曲",
                ImageBase64.imageToBase64(this.context,"keji_h.jpg"),
                302.2F,
                "霍金",
                "978-7530763469",
                "该套装收录了霍金的经典作品：《时间简史》、《果壳中的宇宙》、《大设计》。霍金是当代最重要的广义相对论和宇宙论家，是当今享有国际盛誉的伟人之一，被称为在世的最伟大的科学家之一，还被称为“宇宙之王”。",
                "科技"));
        books.add(new Book(7,
                "美的历程",
                ImageBase64.imageToBase64(this.context,"renwen_mei.jpg"),
                49.4F,
                "李泽厚",
                "978-7530763469",
                "《美的历程》从宏观角度鸟瞰中国数千年的艺术、文学，并作了描述概括和整体美学把握。其中提出了诸如原始远古艺术的“龙飞凤舞”和殷周青铜器艺术的“狞厉的美”，先秦理性精神的“儒道互补”，楚辞、汉赋、汉画像石之“浪漫主义”，“人的觉醒”的魏晋风度，六朝、唐、宋佛像雕塑，宋元山水绘画以及诗、词、曲各具审美三品类，明清时期小说、戏曲由浪漫而感伤而现实之变迁等等重要观念，多发前人之所未发。",
                "人文"));
        books.add(new Book(8,
                "明朝那些事儿",
                ImageBase64.imageToBase64(this.context,"renwen_m.jpg"),
                352.5F,
                "当年明月",
                "978-7559602152",
                "《明朝那些事儿》主要讲述的是从1344年到1644年这三百年间关于明朝的历史，参考《明实录》《明通鉴》《明史》《明史纪事本末》等二十余种明代史料和笔记杂谈，经明史专家毛佩琦审订推荐，俯瞰三百年明史，看到不一样的大明王朝",
                "人文" ));
        books.add(new Book(9,
                "李银河谈亲密关系",
                ImageBase64.imageToBase64(this.context,"shenghuo_l.jpg"),
                50.5F,
                "李银河",
                "978-1087999838",
                "本书从社会学的角度出发，探讨每个人在人生不同阶段遇到的问题--经济独立、婚恋焦虑、婆媳关系、家庭暴力。\n" +
                        "为什么选择单身的人越来越多？谈恋爱时要不要考虑对方的家庭背景？我们该如何看待婚前同居？在面临家庭暴力的时候，我们应该如何保护自己？\n" +
                        "知名社会学家李银河深入剖析54个社会热点话题，旨在带领读者打破亲密关系的刻板印象，帮助大家建立爱与性的全新认知。让大家认清亲密关系的本质，全面地认识自己、更好地理解他人。",
                "生活"));
        books.add(new Book(10,
                "中国国家地理海错图笔记",
                ImageBase64.imageToBase64(this.context,"shenghuo_z.jpg"),
                62.6F,
                "张辰亮",
                "978-7508669069",
                 "海错的“错”，是种类繁多、错杂的意思。\n" +
                         "清代画家兼生物爱好者聂璜绘制的《海错图》，共描绘了300多种生物，几乎涵盖无脊椎动物门和脊索动物门的大部分主要类群，还记载了不少海滨植物，是一本颇具现代博物学风格的奇书。\n" +
                         "但时代所限，书中也有很多不靠谱之处，比如有些动物聂璜未曾亲见，仅根据别人描述绘制的外形，就会有很大失真。关于生物习性的记载，也是真假混杂。\n" +
                         "正因如此，当我开始用今天生物学的角度，对《海错图》中的生物进行分析考证，从他的文字和画作中发现蛛丝马迹，辨别真伪，一步一步推理分析后，鉴定出画中生物的真身——这就像在破案一样，非常过瘾。",
                "生活"));
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
        this.insert(sqLiteDatabase, books);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        super.onUpgrade(sqLiteDatabase, i, i1);
    }

    @SuppressLint("Recycle")
    public List<Book> selectByCategory(SQLiteDatabase db,int categoryId){
        List<Book> books = new ArrayList<>();
        String cid = Integer.toString(categoryId);
        Cursor cursor = db.query(TABLE_NAME,null,"category_id=?",new String[]{cid},null,
                null,null,null);
        if(cursor != null){
            while (cursor.moveToNext()){
                Book book = new Book(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                        cursor.getFloat(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),
                        CategoryMapper.selectById(db,cursor.getInt(7)));
                books.add(book);
            }
            cursor.close();
        }
        return books;
    }

    public Book selectById(SQLiteDatabase db,int bookId){
        Book book = null;
        String cid = Integer.toString(bookId);
        Cursor cursor = db.query(TABLE_NAME,null,"id=?",new String[]{cid},null,
                null,null,null);
        if (cursor.moveToFirst()){
            book = new Book(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getFloat(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),
                    CategoryMapper.selectById(db,cursor.getInt(7)));
        }
        cursor.close();
        return book;
    }

    private void insert(SQLiteDatabase sqLiteDatabase, List<Book> books){
        for (Book c : books) {
            ContentValues cv = new ContentValues();
            cv.put("id", c.getId());
            cv.put("name", c.getName());
            cv.put("coverPic", c.getCoverPic());
            cv.put("price", c.getPrice());
            cv.put("author", c.getAuthor());
            cv.put("isbn", c.getISBN());
            cv.put("description", c.getDescription());
            cv.put("category_id", CategoryMapper.selectByName(sqLiteDatabase, c.getCategory()));
            sqLiteDatabase.insert(TABLE_NAME, "", cv);
        }
    }
}
