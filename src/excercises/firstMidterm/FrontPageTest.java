    package excercises.firstMidterm;

    import java.util.*;
    import java.util.stream.Collectors;

    public class FrontPageTest {
        public static void main(String[] args) {
            // Reading
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            Category[] categories = new Category[parts.length];
            for (int i = 0; i < categories.length; ++i) {
                categories[i] = new Category(parts[i]);
            }
            int n = scanner.nextInt();
            scanner.nextLine();
            FrontPage frontPage = new FrontPage(categories);
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < n; ++i) {
                String title = scanner.nextLine();
                cal = Calendar.getInstance();
                int min = scanner.nextInt();
                cal.add(Calendar.MINUTE, -min);
                Date date = cal.getTime();
                scanner.nextLine();
                String text = scanner.nextLine();
                int categoryIndex = scanner.nextInt();
                scanner.nextLine();
                TextNewsItem tni = new TextNewsItem(title, date, categories[categoryIndex], text);
                frontPage.addNewsItem(tni);
            }

            n = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < n; ++i) {
                String title = scanner.nextLine();
                int min = scanner.nextInt();
                cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE, -min);
                scanner.nextLine();
                Date date = cal.getTime();
                String url = scanner.nextLine();
                int views = scanner.nextInt();
                scanner.nextLine();
                int categoryIndex = scanner.nextInt();
                scanner.nextLine();
                MediaNewsItem mni = new MediaNewsItem(title, date, categories[categoryIndex], url, views);
                frontPage.addNewsItem(mni);
            }
            // Execution
            String category = scanner.nextLine();
            System.out.println(frontPage);
            for(Category c : categories) {
                System.out.println(frontPage.listByCategory(c).size());
            }
            try {
                System.out.println(frontPage.listByCategoryName(category).size());
            } catch(CategoryNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    class Category{
        String categoryName;

        public Category(String categoryName) {
            this.categoryName = categoryName;
        }
    }
    abstract class NewsItem{
        String naslov;
        Date datum;
        Category c;

        public NewsItem(String naslov, Date datum, Category c) {
            this.naslov = naslov;
            this.datum = datum;
            this.c = c;
        }
        abstract String getTeaser();
    }

    class TextNewsItem extends NewsItem{
        String text;

        public TextNewsItem(String naslov, Date datum, Category c, String text) {
            super(naslov, datum, c);
            this.text = text;
        }

        @Override
        public String getTeaser() {
            Date date = new Date();
            long difference = date.getTime() - datum.getTime();
            int minutes = (int) (difference / 60000);
            return String.format("%s\n%d\n%s\n",this.naslov,minutes,
                    text.length() > 80 ? text.substring(0,80) : text);
        }
    }
    class MediaNewsItem extends NewsItem{
        String url;
        int views;

        public MediaNewsItem(String naslov, Date datum, Category c, String url, int views) {
            super(naslov, datum, c);
            this.url = url;
            this.views = views;
        }

        @Override
        public String getTeaser() {
            Date date = new Date();
            long difference = date.getTime() - datum.getTime();
            int minutes = (int) (difference / 60000);
            return String.format("%s\n%d\n%s\n%d\n",naslov,minutes,url,views);
        }
    }
    class FrontPage{
        List<NewsItem> newsItems;
        Category [] categories;

        public FrontPage(Category[] categories) {
            this.categories = categories;
            this.newsItems = new ArrayList<>();
        }
        void addNewsItem(NewsItem newsItem){
            this.newsItems.add(newsItem);
        }
        List<NewsItem> listByCategory(Category category){
            return this.newsItems
                    .stream()
                    .filter(newsItem -> newsItem.c.categoryName.equals(category.categoryName))
                    .collect(Collectors.toList());
        }
        List<NewsItem> listByCategoryName(String category) throws CategoryNotFoundException {
            Arrays.stream(this.categories).filter(category1 -> category1.categoryName.equals(category))
                    .findAny()
                    .orElseThrow(()->new CategoryNotFoundException(String.format("Category %s was not found",category)));
           return this.newsItems.stream()
                    .filter(newsItem -> newsItem.c.categoryName.equals(category))
                    .collect(Collectors.toList());
        }

        @Override
        public String toString() {
            return String.format("%s",this.newsItems.stream().map(NewsItem::getTeaser).collect(Collectors.joining("")));
        }
    }
    class CategoryNotFoundException extends Exception{
        public CategoryNotFoundException(String message) {
            super(message);
        }
    }