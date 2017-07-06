package no.phasfjo.infrastructure.news.news2;

import no.phasfjo.dto.comment.Comment;
import no.phasfjo.dto.news.News2;
import no.phasfjo.infrastructure.comment.JpaCommentDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by phasf on 27.01.2017.
 */
public class News2IT {
    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private JpaNews2Dao jpaNewsDao;
    private JpaCommentDao jpaCommentDao;
    private Date date1;
    private Date date2;
    private Date date3;
    private Date date4;
    private Date date5;
    private Date date6;
    private Date date7;
    private Date date8;


    @Before
    public void setup() throws Exception {
        factory = Persistence.createEntityManagerFactory("TEST");
        entityManager = factory.createEntityManager();
        jpaNewsDao = new JpaNews2Dao(entityManager);
        jpaCommentDao = new JpaCommentDao(entityManager);

        date1 = new Date("12/12/2016 21:19:00");
        date2 = new Date("12/13/2016 21:20:00");
        date3 = new Date("12/14/2016 21:21:00");
        date4 = new Date("12/15/2016 21:22:00");
        date5 = new Date("12/16/2016 21:24:00");
        date6 = new Date("12/17/2016 19:24:00");
        date7 = new Date("12/17/2016 19:40:00");
        date8 = new Date("12/17/2016 22:40:00");

    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        factory.close();
    }
    private String timeZone() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        return calendar.getTimeZone().getDisplayName();
    }

    /*
    CREATE NEWS
     */
    @Test
    //@Ignore("@OrderColumn doesn't seem to work")
    public void persistNews() throws Exception {

        News2 news = new News2("Worst football match ever Man U vs Everton");
        news.addComment(new Comment("ChuckRock", "Manchester U had the ball, but Zlatan fumbled", 1, date1));
        news.addComment(new Comment("ApePie81", "Everton deverton soap you are making me sick!", 2, date2));
        news.addComment(new Comment("RichiRi", "Who owns these apes, I have seen gorillaz playing better!!", 3, date3));
        news.addComment(new Comment("JimmyBindr9", "Looosers with a big L", 4, date4));
        news.addComment(new Comment("ChuckRock", "Really cannot everybody be friends?", 5, date5));

        entityManager.getTransaction().begin();
        jpaNewsDao.persist(news);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        news = entityManager.find(News2.class, news.getId());
        entityManager.refresh(news);
        entityManager.getTransaction().commit();

        assertNotNull(news.toString().isEmpty());
        assertEquals("Worst football match ever Man U vs Everton", news.getContent());
        assertEquals(5, news.getComments().size());

        switch (timeZone()) {
            case "Eastern Standard Time":
                assertEquals("[News2{id=50, content='Worst football match ever Man U vs Everton', comments=[Comment{id=105, nickname='ChuckRock', content='Really cannot everybody be friends?', note=5, postedDate=Sat Dec 17 04:24:00 EST 2016}, Comment{id=104, nickname='JimmyBindr9', content='Looosers with a big L', note=4, postedDate=Fri Dec 16 04:22:00 EST 2016}, Comment{id=103, nickname='RichiRi', content='Who owns these apes, I have seen gorillaz playing better!!', note=3, postedDate=Thu Dec 15 04:21:00 EST 2016}, Comment{id=102, nickname='ApePie81', content='Everton deverton soap you are making me sick!', note=2, postedDate=Wed Dec 14 04:20:00 EST 2016}, Comment{id=101, nickname='ChuckRock', content='Manchester U had the ball, but Zlatan fumbled', note=1, postedDate=Tue Dec 13 04:19:00 EST 2016}]}]", jpaNewsDao.getAll().toString());
                assertEquals("Comment{id=105, nickname='ChuckRock', content='Really cannot everybody be friends?', note=5, postedDate=Sat Dec 17 04:24:00 EST 2016}", news.getComments().get(0).toString());
                assertEquals("Comment{id=104, nickname='JimmyBindr9', content='Looosers with a big L', note=4, postedDate=Fri Dec 16 04:22:00 CET 2016}", news.getComments().get(1).toString());
                assertEquals("Comment{id=103, nickname='RichiRi', content='Who owns these apes, I have seen gorillaz playing better!!', note=3, postedDate=Thu Dec 15 04:21:00 EST 2016}", news.getComments().get(2).toString());
                assertEquals("Comment{id=102, nickname='ApePie81', content='Everton deverton soap you are making me sick!', note=2, postedDate=Wed Dec 14 06:20:00 EST 2016}", news.getComments().get(3).toString());
                assertEquals("Comment{id=101, nickname='ChuckRock', content='Manchester U had the ball, but Zlatan fumbled', note=1, postedDate=Tue Dec 13 06:19:00 EST 2016}", news.getComments().get(4).toString());
                break;
            case "Central Eastern Time":
                assertEquals("[News2{id=50, content='Worst football match ever Man U vs Everton', comments=[Comment{id=105, nickname='ChuckRock', content='Really cannot everybody be friends?', note=5, postedDate=Fri Dec 16 22:24:00 CET 2016}, Comment{id=104, nickname='JimmyBindr9', content='Looosers with a big L', note=4, postedDate=Thu Dec 15 22:22:00 CET 2016}, Comment{id=103, nickname='RichiRi', content='Who owns these apes, I have seen gorillaz playing better!!', note=3, postedDate=Wed Dec 14 22:21:00 CET 2016}, Comment{id=102, nickname='ApePie81', content='Everton deverton soap you are making me sick!', note=2, postedDate=Tue Dec 13 22:20:00 CET 2016}, Comment{id=101, nickname='ChuckRock', content='Manchester U had the ball, but Zlatan fumbled', note=1, postedDate=Mon Dec 12 22:19:00 CET 2016}]}]", jpaNewsDao.getAll().toString());
                assertEquals("Comment{id=105, nickname='ChuckRock', content='Really cannot everybody be friends?', note=5, postedDate=Fri Dec 16 22:24:00 CET 2016}", news.getComments().get(0).toString());
                assertEquals("Comment{id=104, nickname='JimmyBindr9', content='Looosers with a big L', note=4, postedDate=Thu Dec 15 22:22:00 CET 2016}", news.getComments().get(1).toString());
                assertEquals("Comment{id=103, nickname='RichiRi', content='Who owns these apes, I have seen gorillaz playing better!!', note=3, postedDate=Wed Dec 14 22:21:00 CET 2016}", news.getComments().get(2).toString());
                assertEquals("Comment{id=102, nickname='ApePie81', content='Everton deverton soap you are making me sick!', note=2, postedDate=Tue Dec 13 22:20:00 CET 2016}", news.getComments().get(3).toString());
                assertEquals("Comment{id=101, nickname='ChuckRock', content='Manchester U had the ball, but Zlatan fumbled', note=1, postedDate=Mon Dec 12 22:19:00 CET 2016}", news.getComments().get(4).toString());
                break;
            case "Central Standard Time":
                assertEquals("[News2{id=50, content='Worst football match ever Man U vs Everton', comments=[Comment{id=105, nickname='ChuckRock', content='Really cannot everybody be friends?', note=5, postedDate=Fri Dec 16 21:24:00 CST 2016}, Comment{id=104, nickname='JimmyBindr9', content='Looosers with a big L', note=4, postedDate=Thu Dec 15 21:22:00 CST 2016}, Comment{id=103, nickname='RichiRi', content='Who owns these apes, I have seen gorillaz playing better!!', note=3, postedDate=Wed Dec 14 21:21:00 CST 2016}, Comment{id=102, nickname='ApePie81', content='Everton deverton soap you are making me sick!', note=2, postedDate=Tue Dec 13 21:20:00 CST 2016}, Comment{id=101, nickname='ChuckRock', content='Manchester U had the ball, but Zlatan fumbled', note=1, postedDate=Mon Dec 12 21:19:00 CST 2016}]}]", jpaNewsDao.getAll().toString());
                assertEquals("Comment{id=105, nickname='ChuckRock', content='Really cannot everybody be friends?', note=5, postedDate=Fri Dec 16 21:24:00 CST 2016}", news.getComments().get(0).toString());
                assertEquals("Comment{id=104, nickname='JimmyBindr9', content='Looosers with a big L', note=4, postedDate=Thu Dec 15 21:22:00 CST 2016}", news.getComments().get(1).toString());
                assertEquals("Comment{id=103, nickname='RichiRi', content='Who owns these apes, I have seen gorillaz playing better!!', note=3, postedDate=Wed Dec 14 21:21:00 CST 2016}", news.getComments().get(2).toString());
                assertEquals("Comment{id=102, nickname='ApePie81', content='Everton deverton soap you are making me sick!', note=2, postedDate=Tue Dec 13 21:20:00 CST 2016}", news.getComments().get(3).toString());
                assertEquals("Comment{id=101, nickname='ChuckRock', content='Manchester U had the ball, but Zlatan fumbled', note=1, postedDate=Mon Dec 12 21:19:00 CST 2016}", news.getComments().get(4).toString());
                break;
            case "Central European Time":
                assertEquals("[News2{id=50, content='Worst football match ever Man U vs Everton', comments=[Comment{id=105, nickname='ChuckRock', content='Really cannot everybody be friends?', note=5, postedDate=Fri Dec 16 21:24:00 CET 2016}, Comment{id=104, nickname='JimmyBindr9', content='Looosers with a big L', note=4, postedDate=Thu Dec 15 21:22:00 CET 2016}, Comment{id=103, nickname='RichiRi', content='Who owns these apes, I have seen gorillaz playing better!!', note=3, postedDate=Wed Dec 14 21:21:00 CET 2016}, Comment{id=102, nickname='ApePie81', content='Everton deverton soap you are making me sick!', note=2, postedDate=Tue Dec 13 21:20:00 CET 2016}, Comment{id=101, nickname='ChuckRock', content='Manchester U had the ball, but Zlatan fumbled', note=1, postedDate=Mon Dec 12 21:19:00 CET 2016}]}]", jpaNewsDao.getAll().toString());
                assertEquals("Comment{id=105, nickname='ChuckRock', content='Really cannot everybody be friends?', note=5, postedDate=Fri Dec 16 21:24:00 CET 2016}", news.getComments().get(0).toString());
                assertEquals("Comment{id=104, nickname='JimmyBindr9', content='Looosers with a big L', note=4, postedDate=Thu Dec 15 21:22:00 CET 2016}", news.getComments().get(1).toString());
                assertEquals("Comment{id=103, nickname='RichiRi', content='Who owns these apes, I have seen gorillaz playing better!!', note=3, postedDate=Wed Dec 14 21:21:00 CET 2016}", news.getComments().get(2).toString());
                assertEquals("Comment{id=102, nickname='ApePie81', content='Everton deverton soap you are making me sick!', note=2, postedDate=Tue Dec 13 21:20:00 CET 2016}", news.getComments().get(3).toString());
                assertEquals("Comment{id=101, nickname='ChuckRock', content='Manchester U had the ball, but Zlatan fumbled', note=1, postedDate=Mon Dec 12 21:19:00 CET 2016}", news.getComments().get(4).toString());
                break;
            default:
                throw new IllegalArgumentException("Not a timezone");
        }


        //  assertEquals("Really cannot everybody be friends?", news.getComments().get(0).getContent());
        //  assertEquals("Looosers with a big L", news.getComments().get(1).getContent());
        //  assertEquals("Who owns these apes, I have seen gorillaz playing better!!", news.getComments().get(2).getContent());
        //  assertEquals("Everton deverton soap you are making me sick!", news.getComments().get(3).getContent());
        //  assertEquals("Manchester U had the ball, but Zlatan fumbled", news.getComments().get(4).getContent());
    }
    /*
    UPDATE NEWS TEST
     */

    @Test
    public void updateNewsContent() throws Exception {

        News2 news = new News2("Worst football match ever Man U vs Everton");
        news.addComment(new Comment("ChuckRock", "Manchester U had the ball, but Zlatan fumbled", 1, date1));
        news.addComment(new Comment("ApePie81", "Everton deverton soap you are making me sick!", 2, date2));
        news.addComment(new Comment("RichiRi", "Who owns these apes, I have seen gorillaz playing better!!", 3, date3));
        news.addComment(new Comment("JimmyBindr9", "Looosers with a big L", 4, date4));
        news.addComment(new Comment("ChuckRock", "Really cannot everybody be friends?", 5, date5));

        entityManager.getTransaction().begin();
        jpaNewsDao.persist(news);
        entityManager.getTransaction().commit();

        assertNotNull(news.toString().isEmpty());
        assertEquals("Worst football match ever Man U vs Everton", news.getContent());

        news.setContent("Best football match i ever had experience!");
        entityManager.getTransaction().begin();
        boolean update = jpaNewsDao.update(news);
        entityManager.getTransaction().commit();

        assertTrue(update);
        assertNotSame("Worst football match ever Man U vs Everton", news.getContent());

    }

    /*
    DELETE NEWS TEST
     */
    @Test
    public void createANewsWithCommentsAndDeleteComment() throws Exception {

        Comment comment4 = new Comment("JimmyBindr9", "Looosers with a big L", 4, date4);
        Comment comment5 = new Comment("ChuckRock", "Really cannot everybody be friends?", 5, date5);

        News2 news = new News2("Worst football match ever Man U vs Everton");
        news.addComment(new Comment("ChuckRock", "Manchester U had the ball, but Zlatan fumbled", 1, date1));
        news.addComment(new Comment("ApePie81", "Everton deverton soap you are making me sick!", 2, date2));
        news.addComment(new Comment("RichiRi", "Who owns these apes, I have seen gorillaz playing better!!", 3, date3));
        news.addComment((comment4));
        news.addComment((comment5));

        entityManager.getTransaction().begin();
        jpaNewsDao.persist(news);
        entityManager.getTransaction().commit();

        assertEquals(5, jpaCommentDao.getAll().size());

        entityManager.getTransaction().begin();
        jpaNewsDao.delete(50);
        jpaCommentDao.delete(101);
        jpaCommentDao.delete(102);
        jpaCommentDao.delete(103);
        entityManager.getTransaction().commit();

        assertNotSame(5, jpaCommentDao.getAll().size());
        assertEquals(2, jpaCommentDao.getAll().size());
        assertEquals(104, comment4.getId());
        assertEquals(105, comment5.getId());
    }

    /*
    FIND BY ID TEST
     */
    @Test
    public void createTwoNewsWithCommentAndFindById() throws Exception {

        //News one
        News2 news = new News2("Man murder alarm in Elverum!");
        news.addComment(new Comment("ChuckRock", "Does anyone know a 30 year old man in Elverum?", 1, date1));
        news.addComment(new Comment("ApePie81", "No, people make me sick. Why do they kill each other?", 2, date2));
        news.addComment(new Comment("RichiRi", "Do anyone still live in Elverum?", 3, date3));
        news.addComment(new Comment("ApePie81", "Apparently some still do..", 3, date4));

        //News two
        News2 news2 = new News2("Norwegian Camilla (36) in a bomb terror situation in USA.");
        news2.addComment(new Comment("ChuckRock", "Really did someone just prank called the captain?", 1, date1));
        news2.addComment(new Comment("ApePie81", "Oh man! I just hate it when that 'happens'! LoL ", 2, date2));
        news2.addComment(new Comment("JimmyBindr9", "Guys! really? You cannot joke with a serious situation like this!", 3, date3));
        news2.addComment(new Comment("ChuckRock", "Chill out 'JimmyBindr9',  it's just hysterical how people act up on anything!", 4, date4));
        news2.addComment(new Comment("JimmyBindr9", "Well, 'ChuckRock' i don't find it funny and i really think it's unmoral to joke about.", 5, date5));

        entityManager.getTransaction().begin();
        News2 res1 = jpaNewsDao.persist(news);
        News2 res2 = jpaNewsDao.persist(news2);
        entityManager.getTransaction().commit();

        assertTrue(res1.getId() == 50);
        assertTrue(res2.getId() == 51);

        assertNotSame(res1, res2);
        assertEquals(2, jpaNewsDao.getAll().size());
        assertEquals(res1.getContent(), jpaNewsDao.findById(50).getContent());
        assertEquals(res2.getContent(), jpaNewsDao.findById(51).getContent());
        assertEquals(res1.getComments(), jpaNewsDao.findById(50).getComments());
        assertEquals(res2.getComments(), jpaNewsDao.findById(51).getComments());
    }

    @Test
    public void createThreeNewNewsWithCommentsAndGetAll() throws Exception {

        //News one
        News2 news = new News2("David Watne turned down a million dollar salary to continue his job in Tv2!");
        news.addComment(new Comment("ApePie81", "Haha! what a dumb nob! really still working for that ape channel?", 1, date1));
        news.addComment(new Comment("ChuckRock", "Well, 'ApePie81' some of us still love that channel, be a little bit more sensitive. ", 2, date2));
        news.addComment(new Comment("ApePie81", "'ChuckRock' LOL are u gay? U know that old fart is turning senile to be continuing that channel", 3, date3));
        news.addComment(new Comment("Scienc4Life", "'ApePie81' and 'ChuckRock' stop being childish I believe Watne had a good explanation why he choose to stay.", 3, date3));
        news.addComment(new Comment("ApePie81", "Haha! yeah he is what we call a dumb nob!", 4, date4));
        news.addComment(new Comment("JimmyBindr9", "Well I am glad that he is staying. I love his work.", 5, date5));

        //News two
        News2 news2 = new News2("No more christmas julegløgg left!");
        news2.addComment(new Comment("ChuckRock", "Shait in tha house! Everybody runs for Sweden to buy some :p", 1, date1));
        news2.addComment(new Comment("ApePie81", "Yeah, I went there last week it was packed to the rim.", 2, date2));
        news2.addComment(new Comment("WanderBaum", "It's so typical Norwegians to panic when there christmas holidays might lack one small thing. Back home we have to fight to keep head off the water", 3, date3));
        news2.addComment(new Comment("ChuckRock", "haha! 'WanderBaum' were u from Afghanistan or something. LOL!", 4, date4));
        news2.addComment(new Comment("WanderBaum", "No but I have just lost my job, and I have four kids. It's hard to find another job in the oil business", 5, date5));
        news2.addComment(new Comment("ChuckRock", "Oh shit man, I am sorry hope u find something soon", 5, date5));
        news2.addComment(new Comment("WanderBaum", "Thanks. You know 'ChuckRock' u should be a little more polite in this chat feed. People can get offended by your opinions", 6, date6));

        //News three
        News2 news3 = new News2("Idar Vollvik is drowning in IRS tax demands");
        news3.addComment(new Comment("Crzy9", "Good riddance what good has he ever done for us!", 1, date1));
        news3.addComment(new Comment("Anglio3", "What? How can you say a thing like that. He has built so much for the Norwegians people. He's a great entrepreneur.", 2, date2));
        news3.addComment(new Comment("AksLik", "I agree with 'Crzy9' he really sucks my tax money, we don't need a guy like that", 3, date3));
        news3.addComment(new Comment("ChuckRock", "Hey guys, you are just jealous. This is all about the Norwegian mentality of not cheering for each other!", 4, date4));
        news3.addComment(new Comment("WanderBaum", "I agree with 'ChuckRock'. Were i am from we celebrate those who make new jobs in our society.", 5, date5));
        news3.addComment(new Comment("Crzy9", "Haha yeah right! Were u from - Cuba?", 6, date5));
        news3.addComment(new Comment("WanderBaum", "No I am originally from Germany, but I live and work here in Norway oil industry", 7, date7));
        news3.addComment(new Comment("Anglio3", "Hey guys! What happend to the topic 'Vidar Vollevik going bankrupt' or something like that?", 8, date8));

        //ADD ALL NEWS TO LIST
        List<News2> testListOfNews = new ArrayList<>();
        testListOfNews.add(news);
        testListOfNews.add(news2);
        testListOfNews.add(news3);

        entityManager.getTransaction().begin();
        News2 res1 = jpaNewsDao.persist(news);
        News2 res2 = jpaNewsDao.persist(news2);
        News2 res3 = jpaNewsDao.persist(news3);
        entityManager.getTransaction().commit();

        assertTrue(res1.getId() == 50);
        assertEquals(51, res2.getId());
        assertEquals(52, res3.getId());

        assertEquals(testListOfNews.toString(), jpaNewsDao.getAll().toString());
        assertEquals(3, jpaNewsDao.getAll().size());
        assertEquals(21, jpaCommentDao.getAll().size());

    }
}
