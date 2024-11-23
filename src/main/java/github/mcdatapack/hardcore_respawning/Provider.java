package github.mcdatapack.hardcore_respawning;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class Provider {
    public static class Model extends FabricModelProvider {
        public Model(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}


        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {
            itemModelGenerator.register(HardcoreRespawning.RESPAWNER, Models.GENERATED);
        }
    }
    public static class Recipe extends FabricRecipeProvider {
        public Recipe(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        public void generate(RecipeExporter recipeExporter) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, HardcoreRespawning.RESPAWNER)
                    .input('A', Items.GOLDEN_APPLE)
                    .input('B', Items.ENCHANTED_GOLDEN_APPLE)
                    .input('C', Items.TOTEM_OF_UNDYING)
                    .input('D', Items.EMERALD)
                    .input('E', Items.STICK)
                    .pattern("ABC")
                    .pattern("DCB")
                    .pattern("EDA")
                    .criterion(hasItem(Items.ENCHANTED_GOLDEN_APPLE), conditionsFromItem(Items.ENCHANTED_GOLDEN_APPLE))
                    .offerTo(recipeExporter);
        }
    }
}
