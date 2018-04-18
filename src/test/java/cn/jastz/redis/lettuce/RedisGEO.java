package cn.jastz.redis.lettuce;

import io.lettuce.core.GeoArgs;
import me.jastz.common.amap.AMapTemplate;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.geo.Point;

/**
 * GEO 内部存的是sorted set
 *
 * @author zhiwen
 */
public class RedisGEO extends AbstractTest {

    private String amapKey = "aaf82134989a8340917d3c9f5f8f2b4c";
    private AMapTemplate amapTemplate = new AMapTemplate();


    @Test
    public void geoadd() {
        String key = "geoadd-key";
        commands.del(key);
        Point youbao = amapTemplate.opsForGeo().singleGeoCode(amapKey, "深圳市南山区深南大道9996号松日鼎盛大厦8层");
        Point xinzhouqu = amapTemplate.opsForGeo().singleGeoCode(amapKey, "江西省上饶市信州区");
        System.out.println(youbao);
        System.out.println(xinzhouqu);
        commands.geoadd(key, youbao.getX(), youbao.getY(), "友宝");
        commands.geoadd(key, xinzhouqu.getX(), xinzhouqu.getY(), "信州区");
        Assert.assertTrue(commands.zcard(key) == 2);
    }

    @Test
    public void geohash() {
        String key = "geohash-key";
        commands.del(key);
        Point youbao = amapTemplate.opsForGeo().singleGeoCode(amapKey, "深圳市南山区深南大道9996号松日鼎盛大厦8层");
        Point xinzhouqu = amapTemplate.opsForGeo().singleGeoCode(amapKey, "江西省上饶市信州区");
        commands.geoadd(key, youbao.getX(), youbao.getY(), "友宝");
        commands.geoadd(key, xinzhouqu.getX(), xinzhouqu.getY(), "信州区");
        System.out.println(commands.geohash(key, "友宝"));
    }

    @Test
    public void geopos() {
        String key = "geopos-key";
        commands.del(key);
        Point youbao = amapTemplate.opsForGeo().singleGeoCode(amapKey, "深圳市南山区深南大道9996号松日鼎盛大厦8层");
        commands.geoadd(key, youbao.getX(), youbao.getY(), "友宝");
        System.out.println(youbao);
        System.out.println(commands.geopos(key, "友宝"));
    }

    @Test
    public void geodist() {
        String key = "geodist-key";
        commands.del(key);
        Point youbao = amapTemplate.opsForGeo().singleGeoCode(amapKey, "深圳市南山区深南大道9996号松日鼎盛大厦8层");
        Point xinzhouqu = amapTemplate.opsForGeo().singleGeoCode(amapKey, "江西省上饶市信州区");
        commands.geoadd(key, youbao.getX(), youbao.getY(), "youbao");
        commands.geoadd(key, xinzhouqu.getX(), xinzhouqu.getY(), "xinzhouqu");
        System.out.println(commands.geodist(key, "youbao", "xinzhouqus", GeoArgs.Unit.km));
    }

    @Test
    public void georadius() {
        String key = "georadius-key";
        commands.del(key);
        Point songridingsheng = amapTemplate.opsForGeo().singleGeoCode(amapKey, "深圳市南山区深南大道松日鼎盛大厦");
        Point wanlida = amapTemplate.opsForGeo().singleGeoCode(amapKey, "深圳市南山区深南大万利达大厦");
        Point bike = amapTemplate.opsForGeo().singleGeoCode(amapKey, "深圳市南山区深南大比克大厦");
        commands.geoadd(key, songridingsheng.getX(), songridingsheng.getY(), "songridingsheng");
        commands.geoadd(key, bike.getX(), bike.getY(), "bike");
        commands.geoadd(key, wanlida.getX(), wanlida.getY(), "wanlida");
        System.out.println(commands.georadius(key, wanlida.getX(), wanlida.getY(), 2, GeoArgs.Unit.km));
    }

    @Test
    public void georadiusbymember() {
        String key = "georadiusbymember-key";
        commands.del(key);
        Point songridingsheng = amapTemplate.opsForGeo().singleGeoCode(amapKey, "深圳市南山区深南大道松日鼎盛大厦");
        Point wanlida = amapTemplate.opsForGeo().singleGeoCode(amapKey, "深圳市南山区深南大万利达大厦");
        Point bike = amapTemplate.opsForGeo().singleGeoCode(amapKey, "深圳市南山区深南大比克大厦");
        commands.geoadd(key, songridingsheng.getX(), songridingsheng.getY(), "songridingsheng");
        commands.geoadd(key, bike.getX(), bike.getY(), "bike");
        commands.geoadd(key, wanlida.getX(), wanlida.getY(), "wanlida");
        System.out.println(commands.georadiusbymember(key, "songridingsheng", 2, GeoArgs.Unit.km));
    }
}
