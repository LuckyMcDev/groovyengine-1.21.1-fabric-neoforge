package dev.perxenic.groovyengine.api.builder;

/**
 * Public API for recipe creation
 */
public interface RecipeBuilderApi {
    RecipeBuilderApi shaped(String id);
    RecipeBuilderApi shapeless(String id);
    RecipeBuilderApi cooking(String id, String type);

    interface ShapedBuilder {
        ShapedBuilder pattern(String... pattern);
        ShapedBuilder key(String symbol, String item);
        ShapedBuilder output(String item, int count);
        void build();
    }

    interface ShapelessBuilder {
        ShapelessBuilder ingredients(String... ingredients);
        ShapelessBuilder output(String item, int count);
        void build();
    }

    interface CookingBuilder {
        CookingBuilder ingredient(String item);
        CookingBuilder output(String item);
        CookingBuilder experience(float xp);
        CookingBuilder cookingTime(int ticks);
        void build();
    }
}