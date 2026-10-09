/*
package uz.zafar.onlineshoptelegrambot.config;

import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.zafar.onlineshoptelegrambot.db.entity.category.Category;
import uz.zafar.onlineshoptelegrambot.db.entity.category.Product;
import uz.zafar.onlineshoptelegrambot.db.entity.category.ProductType;
import uz.zafar.onlineshoptelegrambot.db.entity.category.ProductTypeImage;
import uz.zafar.onlineshoptelegrambot.db.entity.common.SubscriptionPlan;
import uz.zafar.onlineshoptelegrambot.db.entity.enums.ProductStatus;
import uz.zafar.onlineshoptelegrambot.db.entity.enums.SubscriptionPlanType;
import uz.zafar.onlineshoptelegrambot.db.entity.shop.Shop;
import uz.zafar.onlineshoptelegrambot.db.repositories.SubscriptionPlanRepository;
import uz.zafar.onlineshoptelegrambot.db.repositories.category.CategoryRepository;
import uz.zafar.onlineshoptelegrambot.db.repositories.category.ProductRepository;
import uz.zafar.onlineshoptelegrambot.db.repositories.category.ProductTypeImageRepository;
import uz.zafar.onlineshoptelegrambot.db.repositories.category.ProductTypeRepository;
import uz.zafar.onlineshoptelegrambot.db.repositories.shop.ShopRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Component
public class DataInitializer implements CommandLineRunner {

    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final CategoryRepository categoryRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeImageRepository productTypeImageRepository;
    private final ShopRepository shopRepository;
    private final ProductRepository productRepository;


    public DataInitializer(SubscriptionPlanRepository subscriptionPlanRepository, CategoryRepository categoryRepository, ProductTypeRepository productTypeRepository, ProductTypeImageRepository productTypeImageRepository, ShopRepository shopRepository, ProductRepository productRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
        this.categoryRepository = categoryRepository;
        this.productTypeRepository = productTypeRepository;
        this.productTypeImageRepository = productTypeImageRepository;
        this.shopRepository = shopRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String @NonNull ... args) throws Exception {
        initPlans();
        initCategories();
        initProducts();
    }

    private void initPlans() {
        if (subscriptionPlanRepository.findAll().isEmpty()) {
            String[] prices = {"200000", "140000", "150000", "200000", "140000", "150000", "200000", "140000", "150000"};
            int i = 0;
            for (SubscriptionPlanType value : SubscriptionPlanType.values()) {
                SubscriptionPlan subscriptionPlan = new SubscriptionPlan();
                subscriptionPlan.setName(value);
                subscriptionPlan.setDiscount(false);
                subscriptionPlan.setPrice((value == SubscriptionPlanType.EXPIRED || value == SubscriptionPlanType.TRIAL) ? BigDecimal.ZERO : new BigDecimal(prices[i]));
                subscriptionPlan.setCreatedAt(LocalDateTime.now());
                subscriptionPlan.setUpdatedAt(LocalDateTime.now());
                subscriptionPlan = subscriptionPlanRepository.save(subscriptionPlan);
                i++;
            }
        }
    }

    private void initProducts() {

        if (!productRepository.findAll().isEmpty()) return;

        Shop shop = shopRepository.findAll().get(0);
        Category android = categoryRepository.findBySlug("android").orElseThrow();

        Product samsung = product(
                "Samsung Galaxy S23",
                "Самсунг Галакси S23",
                "Samsung Galaxy S23",
                "Самсунг Галакси S23",
                "SKU-S23",
                android,
                shop
        );

        productRepository.save(samsung);

        // ================== PRODUCT TYPES ==================
        ProductType s23_128 = productType(
                samsung,
                "128GB Qora",
                "128GB Қора",
                "128GB Black",
                "128GB Чёрный",
                new BigDecimal("9500000"),
                15
        );

        ProductType s23_256 = productType(
                samsung,
                "256GB Kumush",
                "256GB Кумуш",
                "256GB Silver",
                "256GB Серебро",
                new BigDecimal("10200000"),
                10
        );

        productTypeRepository.saveAll(List.of(s23_128, s23_256));

        // ================== PRODUCT TYPE IMAGES ==================
        productTypeImageRepository.saveAll(List.of(
                image(s23_128, "https://images.unsplash.com/photo-1610945265064-0e34e5519bbf", true),
                image(s23_128, "https://images.unsplash.com/photo-1580910051074-7c9a7c1fdf33", false),

                image(s23_256, "https://images.unsplash.com/photo-1610945265064-0e34e5519bbf", true),
                image(s23_256, "https://images.unsplash.com/photo-1580910051074-7c9a7c1fdf33", false)
        ));
    }

    private ProductType productType(Product product, String uz, String cyr, String en, String ru,
                                    BigDecimal price, int stock) {
        ProductType pt = new ProductType();
        pt.setProduct(product);
        pt.setNameUz(uz);
        pt.setNameCyr(cyr);
        pt.setNameEn(en);
        pt.setNameRu(ru);
        pt.setPrice(price);
        pt.setStock(stock);
        pt.setDeleted(false);
        return pt;
    }

    private ProductTypeImage image(ProductType pt, String url, boolean main) {
        ProductTypeImage img = new ProductTypeImage();
        img.setProductType(pt);
        img.setImageUrl(url);
        img.setImgName("image-" + System.nanoTime());
        img.setImgSize(500_000L);
        img.setMain(main);
        img.setDeleted(false);
        return img;
    }

    public void initCategories() {

        if (!categoryRepository.findAll().isEmpty()) {
            return;
        }

        // ========== ROOT (6 ta) ==========
        Category electronics = rootCat("Elektronika", "Электроника", "Electronics", "Электроника", "electronics", 1, "https://images.unsplash.com/photo-1518770660439-4636190af475");

        Category clothes = rootCat("Kiyim-kechak", "Кийим-кечак", "Clothes", "Одежда", "clothes", 2, "https://images.unsplash.com/photo-1521335629791-ce4aec67dd47");

        Category home = rootCat("Uy-ro‘zg‘or", "Уй-рўзғор", "Home", "Дом", "home", 3, "https://images.unsplash.com/photo-1505691938895-1758d7feb511");

        Category sport = rootCat("Sport", "Спорт", "Sport", "Спорт", "sport", 4, "https://images.unsplash.com/photo-1517649763962-0c623066013b");

        Category kids = rootCat("Bolalar", "Болалар", "Kids", "Дети", "kids", 5, "https://images.unsplash.com/photo-1601758123927-1964c74c1a34");

        Category beauty = rootCat("Go‘zallik", "Гўзаллик", "Beauty", "Красота", "beauty", 6, "https://images.unsplash.com/photo-1522335789203-aabd1fc54bc9");

        categoryRepository.saveAll(List.of(electronics, clothes, home, sport, kids, beauty));

        // ========== LEVEL 2 ==========
        Category phones = childCat(electronics, "Telefonlar", "Телефонлар", "Phones", "Телефоны", "phones", 10, "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9");

        Category laptops = childCat(electronics, "Noutbuklar", "Ноутбуклар", "Laptops", "Ноутбуки", "laptops", 11, "https://images.unsplash.com/photo-1517336714731-489689fd1ca8");

        Category men = childCat(clothes, "Erkaklar", "Эркаклар", "Men", "Мужское", "men", 12, "https://images.unsplash.com/photo-1520975916090-3105956dac38");

        Category women = childCat(clothes, "Ayollar", "Аёллар", "Women", "Женское", "women", 13, "https://images.unsplash.com/photo-1483985988355-763728e1935b");

        categoryRepository.saveAll(List.of(phones, laptops, men, women));

        // ========== LEVEL 3 ==========
        Category android = childCat(phones, "Android", "Андроид", "Android", "Андроид", "android", 20, "https://images.unsplash.com/photo-1585060544812-6b45742d762f");

        Category iphone = childCat(phones, "iPhone", "Айфон", "iPhone", "Айфон", "iphone", 21, "https://images.unsplash.com/photo-1510557880182-3d4d3cba35a5");

        Category gaming = childCat(laptops, "Gaming", "Гейминг", "Gaming", "Игровые", "gaming", 22, "https://images.unsplash.com/photo-1603302576837-37561b2e2302");

        categoryRepository.saveAll(List.of(android, iphone, gaming));

        // ========== LEVEL 4 (chuqur) ==========
        categoryRepository.saveAll(List.of(childCat(gaming, "RTX Laptoplar", "RTX ноутбуклар", "RTX Laptops", "RTX ноутбуки", "rtx", 30, "https://images.unsplash.com/photo-1611078489935-0cb964de46d6"),

                childCat(gaming, "Core i9", "Core i9", "Core i9", "Core i9", "core-i9", 31, "https://images.unsplash.com/photo-1593642632823-8f785ba67e45")));
    }

    private Category rootCat(String uz, String cyr, String en, String ru, String slug, int order, String image) {
        Category c = new Category();
        c.setNameUz(uz);
        c.setNameCyr(cyr);
        c.setNameEn(en);
        c.setNameRu(ru);
        c.setSlug(slug);
        c.setOrderNumber(order);
        c.setImageUrl(image);
        c.setActive(true);
        c.setDescriptionUz(uz + " kategoriyasidagi mahsulotlar");
        c.setDescriptionCyr(cyr + " категориясидаги махсулотлар");
        c.setDescriptionRu("Товары категории " + ru);
        c.setDescriptionEn("Products from " + en + " category");
        return c;
    }

    private Product product(String uz, String ru, String en, String cyr,
                            String sku, Category category, Shop shop) {
        Product p = new Product();
        p.setNameUz(uz);
        p.setNameRu(ru);
        p.setNameEn(en);
        p.setNameCyr(cyr);
        p.setSku(sku);
        p.setStatus(ProductStatus.OPEN);
        p.setCategory(category);
        p.setShop(shop);
        p.setDescriptionUz(uz + " haqida to‘liq ma’lumot");
        p.setDescriptionCyr(cyr + " ҳақида тўлиқ маълумот");
        p.setDescriptionRu("Подробное описание " + ru);
        p.setDescriptionEn("Full description of " + en);
        return p;
    }

    private Category childCat(Category parent, String uz, String cyr, String en, String ru, String slug, int order, String image) {
        Category c = rootCat(uz, cyr, en, ru, slug, order, image);
        c.setParent(parent);
        return c;
    }


}
*/
package com.example.test.config;

import com.example.test.db.entity.Category;
import com.example.test.db.entity.enums.UserRole;
import com.example.test.db.entity.user.User;
import com.example.test.db.repositories.CategoryRepository;
import com.example.test.db.repositories.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public DataInitializer(CategoryRepository categoryRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createCategory(
                "Food",
                "Yemek",
                "Expenses related to food, restaurants and groceries",
                "Yemek, restoran ve market alışverişleriyle ilgili harcamalar", true, 1
        );
        createCategory(
                "Transport",
                "Ulaşım",
                "Expenses related to transportation and travel",
                "Ulaşım ve seyahat ile ilgili harcamalar",
                true, 2
        );

        createCategory(
                "Shopping",
                "Alışveriş",
                "Expenses related to shopping and purchased products",
                "Alışveriş ve satın alınan ürünlerle ilgili harcamalar",
                true, 3
        );
        createCategory(
                "Bills",
                "Faturalar",
                "Expenses related to electricity, water, internet and other bills",
                "Elektrik, su, internet ve diğer faturalarla ilgili harcamalar",
                true, 4
        );

        createCategory(
                "Entertainment",
                "Eğlence",
                "Expenses related to entertainment, movies, games and activities",
                "Eğlence, sinema, oyun ve aktivitelerle ilgili harcamalar",
                true, 5
        );
        createCategory(
                "Health",
                "Sağlık",
                "Expenses related to medical care, medicine and health services",
                "Sağlık hizmetleri, ilaç ve tıbbi harcamalarla ilgili giderler",
                true, 6
        );
        createCategory(
                "Education",
                "Eğitim",
                "Expenses related to education, courses and learning materials",
                "Eğitim, kurs ve öğrenme materyalleriyle ilgili harcamalar",
                true, 7
        );

        createCategory(
                "Salary",
                "Maaş",
                "Money received from salary and regular employment income",
                "Maaş ve düzenli çalışma gelirlerinden elde edilen para",
                true, 8
        );

        createCategory(
                "Business",
                "İş",
                "Money received from business and commercial activities",
                "İş ve ticari faaliyetlerden elde edilen para",
                true, 9
        );

        createCategory(
                "Investment",
                "Yatırım",
                "Money received from investments and financial activities",
                "Yatırımlar ve finansal faaliyetlerden elde edilen para",
                true, 10
        );
        createCategory(
                "Gift",
                "Hediye",
                "Money received as a gift from another person",
                "Başka bir kişiden hediye olarak alınan para",
                false, 11
        );


        addAdmins();

    }

    private void addAdmins() {
        if (userRepository.findByUsername("admin").isPresent()) {
            return;
        }
        User admin = new User();
        admin.setFullName("Zafar Ziyatov");
        admin.setBirthdate(LocalDate.of(2003, 4, 10));
        admin.setRole(UserRole.ADMIN);
        admin.setPassword(passwordEncoder.encode("122333"));
        admin.setEmail("ziyatovzafar98@gmail.com");
        admin.setUsername("admin");
        admin.setEnabled(true);
        admin.setCreatedAt(LocalDateTime.now());
        admin.setUpdatedAt(LocalDateTime.now());
        userRepository.save(admin);
    }

    private void createCategory(
            String nameEn,
            String nameTr,
            String descriptionEn,
            String descriptionTr,
            Boolean active,
            Integer i
    ) {

        if (categoryRepository.existsByNameEn(nameEn)) {
            return;
        }
        Category category = new Category();
        category.setNameEn(nameEn);
        category.setNameTr(nameTr);
        category.setDescriptionEn(descriptionEn);
        category.setDescriptionTr(descriptionTr);
        category.setActive(active);
        category.setCreatedAt(LocalDateTime.now());
        category.setOrderIndex(i);
        category.setUpdatedAt(LocalDateTime.now());
        categoryRepository.save(category);
    }
}