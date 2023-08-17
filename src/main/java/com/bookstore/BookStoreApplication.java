package com.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BookStoreApplication {
    @Bean
    public WebMvcConfigurer mvcConfigurer(){
        // Cross Origin Request
        // http://localhost:3000/ to 8080
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("http://localhost:3000");
            }
        };

    }

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

//        @Component
//    class Runner implements CommandLineRunner {
//        private PasswordEncoder passwordEncoder;
//        private UserService userService;
//        private RoleService roleService;
//        private BookService bookService;
//        private ReviewService reviewService;
//        private RateService rateService;
//        private OrderService orderService;
//        private OrderItemService orderItemService;
//
//        Runner(PasswordEncoder passwordEncoder, UserService userService, RoleService roleService) {
//            this.passwordEncoder = passwordEncoder;
//            this.userService = userService;
//            this.roleService = roleService;
//        }
//
//        @Override
//        @Transactional
//        public void run(String... args) throws Exception {
//            User user = new User("thien","0382848687","thien@gmail.com",
//                    passwordEncoder.encode("1234"));
//            userService.saveUser(user);
//            Role role = new Role("ROLE_USER");
//            roleService.saveRole(role);

//        }
//        public void TestUserRole(){
//            User user = new User("luongvuxuan","0382848683","luong@gmail.com","12345678");
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            userService.saveUser(user);
//            Role role = new Role("ROLE_USER");
//            roleService.saveRole(role);
//            User user = userService.retrieveByUserName("thienvuvan");
//            Role role = roleService.retrieveByName("ROLE_USER").get();
//            user.addRole(role);
//            userService.updateUser(user);
//        }
//        public void TestUserBook(){
//            User user = new User("thinhvuxuan","0382848685","thinhvuxuan@gmail.com","123456789");
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            userService.saveUser(user);
//            Book book1 = new Book("Java", 10.5d, LocalDate.of(2022, 10, 24), null, "Awesome Java Book", 10);
//            Book book2 = new Book("Python", 20.5d, LocalDate.of(2022, 10, 24), null, "Awesome Python Book", 10);
//            user.addBook(book1);
//            user.addBook(book2);
//            userService.saveUser(user);
//        }
//        public void TestBookPublisher(){
//            //Book book1 = bookService.RetrieveByTitle("Java");
//            Book book2 = bookService.retrieveByTitle("Python");
//            Publisher publisher = new Publisher("KimDong", "Hanoi", "KimDong@gmail.com", "0382848684");
//            //book1.setPublisher(publisher);
//            book2.setPublisher(publisher);
//            //bookService.SaveBook(book1);
//            bookService.saveBook(book2);
//        }
//        public void TestBookAuthor(){
//            Book book1 = bookService.retrieveByTitle("Java");
//            Book book2  = bookService.retrieveByTitle("Python");
//            Author author = new Author("ThienVuVan", "ThienVuVan2003@gmail.com", "0382848684");
//            book1.addAuthor(author);
//            book2.addAuthor(author);
//            bookService.updateBook(book1);
//            bookService.updateBook(book2);
//        }
//        public void TestBookCategory(){
//            Book book1 = bookService.retrieveByTitle("Java");
//            Book book2 = bookService.retrieveByTitle("Python");
//            Category category = new Category("Program");
//            book1.addCategory(category);
//            book2.addCategory(category);
//            bookService.updateBook(book1);
//            bookService.updateBook(book2);
//        }
//        public void TestUserOrder(){
//            User user = userService.retrieveByUserName("thienvuvan");
//            Order order1 = new Order(LocalDate.now(), 20d, "10 CauGiay, Hanoi", false);
//            Order order2 = new Order(LocalDate.now(), 50d, "10 HoTungMau, Hanoi", false);
//            user.addOrder(order1);
//            user.addOrder(order2);
//            userService.updateUser(user);
//        }
//        public void TestUserBookReview(){
//            User user1 = userService.retrieveByUserName("thienvuvan");
//            User user2 = userService.retrieveByUserName("thinhvuxuan");
//            Book book1 = bookService.retrieveByTitle("Java");
//            Book book2 = bookService.retrieveByTitle("Python");
//            Review review1 = new Review(user1, book1, "This book is awesome");
//            Review review2 = new Review(user2, book2, "This book is interesting");
//            reviewService.saveReview(review1);
//            reviewService.saveReview(review2);
//        }
//        public void TestUserRateBook(){
//            User user1 = userService.retrieveByUserName("thienvuvan");
//            User user2 = userService.retrieveByUserName("thinhvuxuan");
//            Book book1 = bookService.retrieveByTitle("Java");
//            Book book2 = bookService.retrieveByTitle("Python");
//            UserBookKey userBookKey1 = new UserBookKey(user1.getId(), book1.getId());
//            UserBookKey userBookKey2 = new UserBookKey(user2.getId(), book2.getId());
//            Rate rate1 = new Rate(userBookKey1, user1, book1, 5);
//            Rate rate2 = new Rate(userBookKey2, user2, book2, 4);
//            rateService.saveRate(rate1);
//            rateService.saveRate(rate2);
//        }
//        public void TestOrderItem(){
//            Optional<Order> order = orderService.retrieveById(1);
//            Book book1 = bookService.retrieveByTitle("Java");
//            Book book2 = bookService.retrieveByTitle("Python");
//            OrderItem orderItem1 = new OrderItem(order.get(), book1, 2);
//            OrderItem orderItem2 = new OrderItem(order.get(), book2, 1);
//            orderItemService.saveOrderItem(orderItem1);
//            orderItemService.saveOrderItem(orderItem2);
//        }
//    }
}
