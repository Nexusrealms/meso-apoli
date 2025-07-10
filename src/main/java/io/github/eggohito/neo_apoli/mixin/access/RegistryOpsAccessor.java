package io.github.eggohito.neo_apoli.mixin.access;

import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({RegistryOps.class})
public interface RegistryOpsAccessor {
    @Accessor("registryInfoGetter")
    RegistryOps.RegistryInfoGetter getRegistryInfoGetter();
    @Mixin({RegistryOps.CachedRegistryInfoGetter.class})
    interface CachedRegistryInfoGetterAccessor {
        @Accessor("registriesLookup")
        RegistryWrapper.WrapperLookup getRegistriesLookup();
    }
}
