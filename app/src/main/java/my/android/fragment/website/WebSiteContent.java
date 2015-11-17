package my.android.fragment.website;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample url for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class WebSiteContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<WebSiteItem> ITEMS = new ArrayList<WebSiteItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, WebSiteItem> ITEM_MAP = new HashMap<String, WebSiteItem>();

    static {
        // Add 3 sample items.
        addItem(new WebSiteItem("百度", "http://www.baidu.com"));
        addItem(new WebSiteItem("2", "Item 2"));
        addItem(new WebSiteItem("3", "Item 3"));
    }

    private static void addItem(WebSiteItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.name, item);
    }

    /**
     * A dummy item representing a piece of url.
     */
    public static class WebSiteItem {
        public String name;
        public String url;

        public WebSiteItem(String name, String url) {
            this.name = name;
            this.url = url;
        }

        @Override
        public String toString() {
            return url;
        }
    }
}
