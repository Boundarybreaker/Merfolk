package space.bbkr.merfolk;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import space.bbkr.merfolk.entity.MerfolkEntity;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallBlock;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Merfolk implements ModInitializer {
	public static final String MODID = "merfolk";

	public static final Logger logger = LogManager.getLogger();

	public static final EntityType<MerfolkEntity> MERFOLK = Registry.register(Registry.ENTITY_TYPE,
			new Identifier(MODID, MODID),
			FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MerfolkEntity::new)
					.dimensions(EntityDimensions.fixed(0.6F, 1.95F))
					.fireImmune()
					.build()
	);

	public static final Block SUNKEN_SANDSTONE = register("sunken_sandstone", new Block(FabricBlockSettings.copy(Blocks.SANDSTONE)), ItemGroup.BUILDING_BLOCKS);
	public static final Block SUNKEN_SANDSTONE_WALL = register("sunken_sandstone_wall", new WallBlock(FabricBlockSettings.copy(Blocks.SANDSTONE)), ItemGroup.DECORATIONS);

	@Override
	public void onInitialize() {

	}

	private static Block register(String name, Block block, ItemGroup group) {
		Block ret = Registry.register(Registry.BLOCK, new Identifier(MODID, name), block);
		Registry.register(Registry.ITEM, new Identifier(MODID, name), new BlockItem(ret, new Item.Settings().group(group)));
		return ret;
	}
}
