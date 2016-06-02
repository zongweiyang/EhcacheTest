import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

import java.io.File;

/**
 * Created by yzw on 6/2/16.
 */
public class EhcacheTest {

    public static void main(String[] args) {
        PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence("/home/yzw" + File.separator + "myData"))
                .withCache("threeTieredCache",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder()
                                        .heap(10, EntryUnit.ENTRIES)
                                        .offheap(1, MemoryUnit.MB)
                                        .disk(20, MemoryUnit.MB)
                        )
                ).build(true);

        Cache<Long,String> cache = persistentCacheManager.getCache("threeTieredCache",Long.class,String.class);
        System.out.println(cache.get((long) 1));
        for (int i = 0 ;i<10;i++ ){
            cache.put((long) i,"hellow"+i);
        }

        System.out.println(cache.get((long) 1));


        //persistentCacheManager.close();
    }
}
