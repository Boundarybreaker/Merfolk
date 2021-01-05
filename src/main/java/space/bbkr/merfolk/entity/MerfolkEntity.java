package space.bbkr.merfolk.entity;

import space.bbkr.merfolk.Merfolk;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.PiglinActivity;
import net.minecraft.world.World;

public class MerfolkEntity extends AbstractPiglinEntity {

	public MerfolkEntity(EntityType<? extends AbstractPiglinEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected boolean canHunt() {
		return false;
	}

	@Override
	public PiglinActivity getActivity() {
		return null;
	}

	@Override
	protected void playZombificationSound() {

	}

	@Override
	public boolean shouldZombify() {
		return super.shouldZombify();
	}
}
