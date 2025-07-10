package io.github.eggohito.neo_apoli.mixin.misc;

import io.github.eggohito.neo_apoli.power.PowerManager;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.tag.TagManagerLoader;
import net.minecraft.server.DataPackContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DataPackContents.class)
public abstract class DataPackContentsMixin {
	//TODO TEST IF I BROKE THIS
	@Inject(method = "repopulateTags", at = @At("TAIL"))
	private static void applyCustomPendingTagsAndValidateCustomElements(DynamicRegistryManager dynamicRegistryManager, TagManagerLoader.RegistryTags<?> tags, CallbackInfo ci) {


		PowerManager.validate(dynamicRegistryManager);
		PowerManager.applyPendingTags();

	}

}
