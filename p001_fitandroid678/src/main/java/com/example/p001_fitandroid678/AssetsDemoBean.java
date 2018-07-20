package com.example.p001_fitandroid678;

import java.io.Serializable;
import java.util.List;

public class AssetsDemoBean implements Serializable {


    /**
     * status : 0
     * description : 成功识别商品
     * command : 10001
     * info : {"wine_id":"228091918","wyear":"","name":"爱柏酒庄悠盖特红葡萄酒","name_en":"Albert Bichot Les Dames Huguettes","pic_url":false,"country_ch":"法国","country_en":"France","country_url":"http://9edit.9kacha.com/country_html/100005/146e951987de1b02d029c4ebb1f4a9fc0DD263932CE798E33AF07369AA415B07/info.html","region_ch":"勃艮第丘（勃艮第）","region_en":"Coteaux Bourguignons(Bourgogne)","region_url":"http://9edit.9kacha.com/region_html/200004/4de3907403518ebed3146492c416097422F394319051B081BDE509C80F3CDB2D/info.html","sub_region_ch":"勃艮第-上尼伊丘（上夜丘）","sub_region_en":"Bourgogne Hautes Cotes de Nuits（Hautes-Cotes de Nuits）","sub_region_url":"http://9edit.9kacha.com/sub_region_html/200034/d4e5ae87f8c0d315bf13f5a66415016a40AA00B506ADB5B13FC58F79000EE51C/info.html","village_region_ch":"","village_region_en":"","vellage_region_url":"http://9edit.9kacha.com/village_region_html/201155/35befc2f7b618a708f0350446a34b273583C99FD9CB5F3BBC8DB879E2BF1F12A/info.html","winery_ch":"爱柏酒庄","winery_en":"Albert Bichot","winery_url":"http://9edit.9kacha.com/winery_html/10080/b2aeeccdf00762bd6f0335e208f01e0e5AF3271137D08A2DD5E2AD95974CF6FE/info.html","wine_series_ch":"","wine_series_en":"","wine_series_url":"","level_ch":"法定地区葡萄酒","level_en":"AOC(AOP)","level_url":"http://9edit.9kacha.com/level_html/100011/6e86d14e54d24b8102e35a6ff5584a5439BAD7F2C04330CD871EB453E9B0EC5E/info.html","capacity":"750ML","alcohol":"","breathing":"30分钟","taste_temp":"14-18℃","type_ch":"红葡萄酒","type_en":"Red Wine","sugar_ch":"干型","sugar_en":"Dry","sugar_url":"http://9edit.9kacha.com/sugar_html/1/b10279866e84350133e1ca5eb15f1041770576D44E408AFA9EE84F36758D829C/info.html","packaging_ch":"瓶装","packaging_en":"Bottle","taste_ch":"酒体轻盈，有柔滑如天鹅绒般的口感，容易入口。","taste_en":"","aroma_ch":"带着新鲜草莓、覆盆子和樱桃的香气。","aroma_en":"","color_ch":"宝石红色","color_en":"Ruby","recipe_pair_ch":"较熟的红肉、鱼类、浓郁口味的贝壳料理、香肠、意大利面。","recipe_pair_en":"","occasion_ch":"情侣 聚会 自饮 商务","occasion_en":"business、meeting、Have A Drink","wine_desc_ch":"此酒呈宝石红色，带着新鲜草莓、覆盆子和樱桃的香气，酒体轻盈，有柔滑如天鹅绒般的口感，容易入口。","wine_desc_en":"","storage_ch":"阴凉处平放避光","storage_en":"Avoid light shade flat","cork_ch":"软木塞","cork_en":"Cork","openning_ch":"开瓶器","openning_en":"Corkscrew","grape_variety":[{"name_ch":"黑比诺","name_en":"Pinot Noir","web_url_ch":"http://9edit.9kacha.com/grape_variety_html/163/937e15d6a62daa7b65f02ec7616900724E5174CECF0E56080A6B712FCDEDD41A/info.html"}],"grade":[],"search_count":"33","retail_count":"0"}
     */

    private String status;
    private String description;
    private String command;
    /**
     * wine_id : 228091918
     * wyear :
     * name : 爱柏酒庄悠盖特红葡萄酒
     * name_en : Albert Bichot Les Dames Huguettes
     * pic_url : false
     * country_ch : 法国
     * country_en : France
     * country_url : http://9edit.9kacha.com/country_html/100005/146e951987de1b02d029c4ebb1f4a9fc0DD263932CE798E33AF07369AA415B07/info.html
     * region_ch : 勃艮第丘（勃艮第）
     * region_en : Coteaux Bourguignons(Bourgogne)
     * region_url : http://9edit.9kacha.com/region_html/200004/4de3907403518ebed3146492c416097422F394319051B081BDE509C80F3CDB2D/info.html
     * sub_region_ch : 勃艮第-上尼伊丘（上夜丘）
     * sub_region_en : Bourgogne Hautes Cotes de Nuits（Hautes-Cotes de Nuits）
     * sub_region_url : http://9edit.9kacha.com/sub_region_html/200034/d4e5ae87f8c0d315bf13f5a66415016a40AA00B506ADB5B13FC58F79000EE51C/info.html
     * village_region_ch :
     * village_region_en :
     * vellage_region_url : http://9edit.9kacha.com/village_region_html/201155/35befc2f7b618a708f0350446a34b273583C99FD9CB5F3BBC8DB879E2BF1F12A/info.html
     * winery_ch : 爱柏酒庄
     * winery_en : Albert Bichot
     * winery_url : http://9edit.9kacha.com/winery_html/10080/b2aeeccdf00762bd6f0335e208f01e0e5AF3271137D08A2DD5E2AD95974CF6FE/info.html
     * wine_series_ch :
     * wine_series_en :
     * wine_series_url :
     * level_ch : 法定地区葡萄酒
     * level_en : AOC(AOP)
     * level_url : http://9edit.9kacha.com/level_html/100011/6e86d14e54d24b8102e35a6ff5584a5439BAD7F2C04330CD871EB453E9B0EC5E/info.html
     * capacity : 750ML
     * alcohol :
     * breathing : 30分钟
     * taste_temp : 14-18℃
     * type_ch : 红葡萄酒
     * type_en : Red Wine
     * sugar_ch : 干型
     * sugar_en : Dry
     * sugar_url : http://9edit.9kacha.com/sugar_html/1/b10279866e84350133e1ca5eb15f1041770576D44E408AFA9EE84F36758D829C/info.html
     * packaging_ch : 瓶装
     * packaging_en : Bottle
     * taste_ch : 酒体轻盈，有柔滑如天鹅绒般的口感，容易入口。
     * taste_en :
     * aroma_ch : 带着新鲜草莓、覆盆子和樱桃的香气。
     * aroma_en :
     * color_ch : 宝石红色
     * color_en : Ruby
     * recipe_pair_ch : 较熟的红肉、鱼类、浓郁口味的贝壳料理、香肠、意大利面。
     * recipe_pair_en :
     * occasion_ch : 情侣 聚会 自饮 商务
     * occasion_en : business、meeting、Have A Drink
     * wine_desc_ch : 此酒呈宝石红色，带着新鲜草莓、覆盆子和樱桃的香气，酒体轻盈，有柔滑如天鹅绒般的口感，容易入口。
     * wine_desc_en :
     * storage_ch : 阴凉处平放避光
     * storage_en : Avoid light shade flat
     * cork_ch : 软木塞
     * cork_en : Cork
     * openning_ch : 开瓶器
     * openning_en : Corkscrew
     * grape_variety : [{"name_ch":"黑比诺","name_en":"Pinot Noir","web_url_ch":"http://9edit.9kacha.com/grape_variety_html/163/937e15d6a62daa7b65f02ec7616900724E5174CECF0E56080A6B712FCDEDD41A/info.html"}]
     * grade : []
     * search_count : 33
     * retail_count : 0
     */

    private InfoBean info;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean implements Serializable {
        private String wine_id;
        private String wyear;
        private String name;
        private String name_en;
        private String pic_url;
        private String country_ch;
        private String country_en;
        private String country_url;
        private String region_ch;
        private String region_en;
        private String region_url;
        private String sub_region_ch;
        private String sub_region_en;
        private String sub_region_url;
        private String village_region_ch;
        private String village_region_en;
        private String village_region_url;
        private String winery_ch;
        private String winery_en;
        private String winery_url;
        private String wine_series_ch;
        private String wine_series_en;
        private String wine_series_url;
        private String level_ch;
        private String level_en;
        private String level_url;
        private String capacity;
        private String alcohol;
        private String breathing;
        private String taste_temp;
        private String type_ch;
        private String type_en;
        private String type_url;
        private String sugar_ch;
        private String sugar_en;
        private String sugar_url;
        private String packaging_ch;
        private String packaging_en;
        private String taste_ch;
        private String taste_en;
        private String aroma_ch;
        private String aroma_en;
        private String color_ch;
        private String color_en;
        private String recipe_pair_ch;
        private String recipe_pair_en;
        private String occasion_ch;
        private String occasion_en;
        private String wine_desc_ch;
        private String wine_desc_en;
        private String storage_ch;
        private String storage_en;
        private String cork_ch;
        private String cork_en;
        private String openning_ch;
        private String openning_en;
        private String search_count;
        private String retail_count;

        @Override
        public String toString() {
            return "InfoBean{" +
                    "wine_id='" + wine_id + '\'' +
                    ", wyear='" + wyear + '\'' +
                    ", name='" + name + '\'' +
                    ", name_en='" + name_en + '\'' +
                    ", pic_url='" + pic_url + '\'' +
                    ", country_ch='" + country_ch + '\'' +
                    ", country_en='" + country_en + '\'' +
                    ", country_url='" + country_url + '\'' +
                    ", region_ch='" + region_ch + '\'' +
                    ", region_en='" + region_en + '\'' +
                    ", region_url='" + region_url + '\'' +
                    ", sub_region_ch='" + sub_region_ch + '\'' +
                    ", sub_region_en='" + sub_region_en + '\'' +
                    ", sub_region_url='" + sub_region_url + '\'' +
                    ", village_region_ch='" + village_region_ch + '\'' +
                    ", village_region_en='" + village_region_en + '\'' +
                    ", vellage_region_url='" + village_region_url + '\'' +
                    ", winery_ch='" + winery_ch + '\'' +
                    ", winery_en='" + winery_en + '\'' +
                    ", winery_url='" + winery_url + '\'' +
                    ", wine_series_ch='" + wine_series_ch + '\'' +
                    ", wine_series_en='" + wine_series_en + '\'' +
                    ", wine_series_url='" + wine_series_url + '\'' +
                    ", level_ch='" + level_ch + '\'' +
                    ", level_en='" + level_en + '\'' +
                    ", level_url='" + level_url + '\'' +
                    ", capacity='" + capacity + '\'' +
                    ", alcohol='" + alcohol + '\'' +
                    ", breathing='" + breathing + '\'' +
                    ", taste_temp='" + taste_temp + '\'' +
                    ", type_ch='" + type_ch + '\'' +
                    ", type_en='" + type_en + '\'' +
                    ", type_url='" + type_url + '\'' +
                    ", sugar_ch='" + sugar_ch + '\'' +
                    ", sugar_en='" + sugar_en + '\'' +
                    ", sugar_url='" + sugar_url + '\'' +
                    ", packaging_ch='" + packaging_ch + '\'' +
                    ", packaging_en='" + packaging_en + '\'' +
                    ", taste_ch='" + taste_ch + '\'' +
                    ", taste_en='" + taste_en + '\'' +
                    ", aroma_ch='" + aroma_ch + '\'' +
                    ", aroma_en='" + aroma_en + '\'' +
                    ", color_ch='" + color_ch + '\'' +
                    ", color_en='" + color_en + '\'' +
                    ", recipe_pair_ch='" + recipe_pair_ch + '\'' +
                    ", recipe_pair_en='" + recipe_pair_en + '\'' +
                    ", occasion_ch='" + occasion_ch + '\'' +
                    ", occasion_en='" + occasion_en + '\'' +
                    ", wine_desc_ch='" + wine_desc_ch + '\'' +
                    ", wine_desc_en='" + wine_desc_en + '\'' +
                    ", storage_ch='" + storage_ch + '\'' +
                    ", storage_en='" + storage_en + '\'' +
                    ", cork_ch='" + cork_ch + '\'' +
                    ", cork_en='" + cork_en + '\'' +
                    ", openning_ch='" + openning_ch + '\'' +
                    ", openning_en='" + openning_en + '\'' +
                    ", search_count='" + search_count + '\'' +
                    ", retail_count='" + retail_count + '\'' +
                    ", sign='" + sign + '\'' +
                    ", grape_variety=" + grape_variety +
                    ", grade=" + grade +
                    '}';
        }

        public String getType_url() {
            return type_url;
        }

        public void setType_url(String type_url) {
            this.type_url = type_url;
        }

        private String sign;  //非回执报文中字段
        /**
         * name_ch : 黑比诺
         * name_en : Pinot Noir
         * web_url_ch : http://9edit.9kacha.com/grape_variety_html/163/937e15d6a62daa7b65f02ec7616900724E5174CECF0E56080A6B712FCDEDD41A/info.html
         */

        private List<GrapeVarietyBean> grape_variety;
        private List<?> grade;

        public String getWine_id() {
            return wine_id;
        }

        public void setWine_id(String wine_id) {
            this.wine_id = wine_id;
        }

        public String getWyear() {
            return wyear;
        }

        public void setWyear(String wyear) {
            this.wyear = wyear;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getCountry_ch() {
            return country_ch;
        }

        public void setCountry_ch(String country_ch) {
            this.country_ch = country_ch;
        }

        public String getCountry_en() {
            return country_en;
        }

        public void setCountry_en(String country_en) {
            this.country_en = country_en;
        }

        public String getCountry_url() {
            return country_url;
        }

        public void setCountry_url(String country_url) {
            this.country_url = country_url;
        }

        public String getRegion_ch() {
            return region_ch;
        }

        public void setRegion_ch(String region_ch) {
            this.region_ch = region_ch;
        }

        public String getRegion_en() {
            return region_en;
        }

        public void setRegion_en(String region_en) {
            this.region_en = region_en;
        }

        public String getRegion_url() {
            return region_url;
        }

        public void setRegion_url(String region_url) {
            this.region_url = region_url;
        }

        public String getSub_region_ch() {
            return sub_region_ch;
        }

        public void setSub_region_ch(String sub_region_ch) {
            this.sub_region_ch = sub_region_ch;
        }

        public String getSub_region_en() {
            return sub_region_en;
        }

        public void setSub_region_en(String sub_region_en) {
            this.sub_region_en = sub_region_en;
        }

        public String getSub_region_url() {
            return sub_region_url;
        }

        public void setSub_region_url(String sub_region_url) {
            this.sub_region_url = sub_region_url;
        }

        public String getVillage_region_ch() {
            return village_region_ch;
        }

        public void setVillage_region_ch(String village_region_ch) {
            this.village_region_ch = village_region_ch;
        }

        public String getVillage_region_en() {
            return village_region_en;
        }

        public void setVillage_region_en(String village_region_en) {
            this.village_region_en = village_region_en;
        }

        public String getVellage_region_url() {
            return village_region_url;
        }

        public void setVellage_region_url(String vellage_region_url) {
            this.village_region_url = vellage_region_url;
        }

        public String getWinery_ch() {
            return winery_ch;
        }

        public void setWinery_ch(String winery_ch) {
            this.winery_ch = winery_ch;
        }

        public String getWinery_en() {
            return winery_en;
        }

        public void setWinery_en(String winery_en) {
            this.winery_en = winery_en;
        }

        public String getWinery_url() {
            return winery_url;
        }

        public void setWinery_url(String winery_url) {
            this.winery_url = winery_url;
        }

        public String getWine_series_ch() {
            return wine_series_ch;
        }

        public void setWine_series_ch(String wine_series_ch) {
            this.wine_series_ch = wine_series_ch;
        }

        public String getWine_series_en() {
            return wine_series_en;
        }

        public void setWine_series_en(String wine_series_en) {
            this.wine_series_en = wine_series_en;
        }

        public String getWine_series_url() {
            return wine_series_url;
        }

        public void setWine_series_url(String wine_series_url) {
            this.wine_series_url = wine_series_url;
        }

        public String getLevel_ch() {
            return level_ch;
        }

        public void setLevel_ch(String level_ch) {
            this.level_ch = level_ch;
        }

        public String getLevel_en() {
            return level_en;
        }

        public void setLevel_en(String level_en) {
            this.level_en = level_en;
        }

        public String getLevel_url() {
            return level_url;
        }

        public void setLevel_url(String level_url) {
            this.level_url = level_url;
        }

        public String getCapacity() {
            return capacity;
        }

        public void setCapacity(String capacity) {
            this.capacity = capacity;
        }

        public String getAlcohol() {
            return alcohol;
        }

        public void setAlcohol(String alcohol) {
            this.alcohol = alcohol;
        }

        public String getBreathing() {
            return breathing;
        }

        public void setBreathing(String breathing) {
            this.breathing = breathing;
        }

        public String getTaste_temp() {
            return taste_temp;
        }

        public void setTaste_temp(String taste_temp) {
            this.taste_temp = taste_temp;
        }

        public String getType_ch() {
            return type_ch;
        }

        public void setType_ch(String type_ch) {
            this.type_ch = type_ch;
        }

        public String getType_en() {
            return type_en;
        }

        public void setType_en(String type_en) {
            this.type_en = type_en;
        }

        public String getSugar_ch() {
            return sugar_ch;
        }

        public void setSugar_ch(String sugar_ch) {
            this.sugar_ch = sugar_ch;
        }

        public String getSugar_en() {
            return sugar_en;
        }

        public void setSugar_en(String sugar_en) {
            this.sugar_en = sugar_en;
        }

        public String getSugar_url() {
            return sugar_url;
        }

        public void setSugar_url(String sugar_url) {
            this.sugar_url = sugar_url;
        }

        public String getPackaging_ch() {
            return packaging_ch;
        }

        public void setPackaging_ch(String packaging_ch) {
            this.packaging_ch = packaging_ch;
        }

        public String getPackaging_en() {
            return packaging_en;
        }

        public void setPackaging_en(String packaging_en) {
            this.packaging_en = packaging_en;
        }

        public String getTaste_ch() {
            return taste_ch;
        }

        public void setTaste_ch(String taste_ch) {
            this.taste_ch = taste_ch;
        }

        public String getTaste_en() {
            return taste_en;
        }

        public void setTaste_en(String taste_en) {
            this.taste_en = taste_en;
        }

        public String getAroma_ch() {
            return aroma_ch;
        }

        public void setAroma_ch(String aroma_ch) {
            this.aroma_ch = aroma_ch;
        }

        public String getAroma_en() {
            return aroma_en;
        }

        public void setAroma_en(String aroma_en) {
            this.aroma_en = aroma_en;
        }

        public String getColor_ch() {
            return color_ch;
        }

        public void setColor_ch(String color_ch) {
            this.color_ch = color_ch;
        }

        public String getColor_en() {
            return color_en;
        }

        public void setColor_en(String color_en) {
            this.color_en = color_en;
        }

        public String getRecipe_pair_ch() {
            return recipe_pair_ch;
        }

        public void setRecipe_pair_ch(String recipe_pair_ch) {
            this.recipe_pair_ch = recipe_pair_ch;
        }

        public String getRecipe_pair_en() {
            return recipe_pair_en;
        }

        public void setRecipe_pair_en(String recipe_pair_en) {
            this.recipe_pair_en = recipe_pair_en;
        }

        public String getOccasion_ch() {
            return occasion_ch;
        }

        public void setOccasion_ch(String occasion_ch) {
            this.occasion_ch = occasion_ch;
        }

        public String getOccasion_en() {
            return occasion_en;
        }

        public void setOccasion_en(String occasion_en) {
            this.occasion_en = occasion_en;
        }

        public String getWine_desc_ch() {
            return wine_desc_ch;
        }

        public void setWine_desc_ch(String wine_desc_ch) {
            this.wine_desc_ch = wine_desc_ch;
        }

        public String getWine_desc_en() {
            return wine_desc_en;
        }

        public void setWine_desc_en(String wine_desc_en) {
            this.wine_desc_en = wine_desc_en;
        }

        public String getStorage_ch() {
            return storage_ch;
        }

        public void setStorage_ch(String storage_ch) {
            this.storage_ch = storage_ch;
        }

        public String getStorage_en() {
            return storage_en;
        }

        public void setStorage_en(String storage_en) {
            this.storage_en = storage_en;
        }

        public String getCork_ch() {
            return cork_ch;
        }

        public void setCork_ch(String cork_ch) {
            this.cork_ch = cork_ch;
        }

        public String getCork_en() {
            return cork_en;
        }

        public void setCork_en(String cork_en) {
            this.cork_en = cork_en;
        }

        public String getOpenning_ch() {
            return openning_ch;
        }

        public void setOpenning_ch(String openning_ch) {
            this.openning_ch = openning_ch;
        }

        public String getOpenning_en() {
            return openning_en;
        }

        public void setOpenning_en(String openning_en) {
            this.openning_en = openning_en;
        }

        public String getSearch_count() {
            return search_count;
        }

        public void setSearch_count(String search_count) {
            this.search_count = search_count;
        }

        public String getRetail_count() {
            return retail_count;
        }

        public void setRetail_count(String retail_count) {
            this.retail_count = retail_count;
        }

        public List<GrapeVarietyBean> getGrape_variety() {
            return grape_variety;
        }

        public void setGrape_variety(List<GrapeVarietyBean> grape_variety) {
            this.grape_variety = grape_variety;
        }

        public List<?> getGrade() {
            return grade;
        }

        public void setGrade(List<?> grade) {
            this.grade = grade;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public static class GrapeVarietyBean implements Serializable {
            private String name_ch;
            private String name_en;
            private String web_url_ch;

            public String getName_ch() {
                return name_ch;
            }

            public void setName_ch(String name_ch) {
                this.name_ch = name_ch;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public String getWeb_url_ch() {
                return web_url_ch;
            }

            public void setWeb_url_ch(String web_url_ch) {
                this.web_url_ch = web_url_ch;
            }
        }
    }
}
