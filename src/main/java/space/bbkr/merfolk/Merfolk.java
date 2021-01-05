package space.bbkr.merfolk;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import space.bbkr.merfolk.entity.MerfolkEntity;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
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

	@Override
	public void onInitialize() {

	}
}
