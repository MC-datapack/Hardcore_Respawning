package github.mcdatapack.hardcore_respawning;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HardcoreRespawning implements ModInitializer {
	public static final String MOD_ID = "hardcore_respawning";
	public static final Logger logger = LoggerFactory.getLogger(MOD_ID);

	public static final Item RESPAWNER = register("respawner", new Item(new Item.Settings()
			.fireproof()
			.maxCount(1)
			.rarity(Rarity.EPIC)
	));

	public static final Text title = Text.translatable("itemGroup."+ MOD_ID);
	public static final ItemGroup group = register("group", FabricItemGroup.builder()
			.displayName(title)
			.icon(RESPAWNER::getDefaultStack)
			.entries((displayContext, entries) -> entries.add(RESPAWNER))
			.build());


	@Override
	public void onInitialize() {
		logger.info("Loading Hardcore Respawning");
	}

	public static Item register(String id, Item item) {
		return Registry.register(Registries.ITEM, Identifier.of(MOD_ID,id), item);
	}

	public static <T extends ItemGroup> T register(String name, T itemGroup) {
		return Registry.register(Registries.ITEM_GROUP, Identifier.of(MOD_ID, name), itemGroup);
	}
}