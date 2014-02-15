// Date: 24/01/2014 21:58:10
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX
package evilcraft.render.models;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import evilcraft.entities.monster.PoisonousLibelle;

/**
 * @author Davivs69
 */
public class PoisonousLibelleModel extends ModelBase {
    //fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer ass;
    ModelRenderer Right_L_wing;
    ModelRenderer Left_L_wing;
    ModelRenderer Right_M_wing;
    ModelRenderer Left_M_wing;
    
    private List<ModelRenderer> wings_left = new LinkedList<ModelRenderer>();
    private List<ModelRenderer> wings_right = new LinkedList<ModelRenderer>();

    /**
     * Make a new instance.
     */
    public PoisonousLibelleModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;

        head = new ModelRenderer(this, 25, 0);
        head.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
        head.setRotationPoint(0.5F, 10F, 7F);
        head.setTextureSize(64, 135);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        body = new ModelRenderer(this, 38, 0);
        body.addBox(-1F, -1F, -11F, 2, 2, 11);
        body.setRotationPoint(0.5F, 10F, 6F);
        body.setTextureSize(64, 135);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        ass = new ModelRenderer(this, 1, 0);
        ass.addBox(-0.5F, -0.5F, -10F, 1, 1, 10);
        ass.setRotationPoint(0.5F, 10F, -5F);
        ass.setTextureSize(64, 135);
        ass.mirror = true;
        setRotation(ass, 0F, 0F, 0F);
        Right_L_wing = new ModelRenderer(this, 0, 17);
        Right_L_wing.addBox(-8F, 0F, -2F, 8, 1, 4);
        Right_L_wing.setRotationPoint(0F, 9F, 3F);
        Right_L_wing.setTextureSize(64, 135);
        Right_L_wing.mirror = true;
        setRotation(Right_L_wing, 0F, 0F, 0.2617994F);
        Left_L_wing = new ModelRenderer(this, 24, 17);
        Left_L_wing.addBox(0F, 0F, -2F, 8, 1, 4);
        Left_L_wing.setRotationPoint(0.5F, 9F, 3F);
        Left_L_wing.setTextureSize(64, 135);
        Left_L_wing.mirror = true;
        setRotation(Left_L_wing, 0F, 0F, -0.2617994F);
        Right_M_wing = new ModelRenderer(this, 0, 23);
        Right_M_wing.addBox(-6F, 0F, -1.5F, 6, 1, 3);
        Right_M_wing.setRotationPoint(0F, 9F, -2F);
        Right_M_wing.setTextureSize(64, 135);
        Right_M_wing.mirror = true;
        setRotation(Right_M_wing, 0F, 0F, 0.2617994F);
        Left_M_wing = new ModelRenderer(this, 18, 23);
        Left_M_wing.addBox(0F, 0F, -1.5F, 6, 1, 3);
        Left_M_wing.setRotationPoint(1F, 9F, -2F);
        Left_M_wing.setTextureSize(64, 135);
        Left_M_wing.mirror = true;
        setRotation(Left_M_wing, 0F, 0F, -0.2617994F);
        
        wings_left.add(Left_L_wing);
        wings_left.add(Left_M_wing);
        wings_right.add(Right_L_wing);
        wings_right.add(Right_M_wing);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);        
        head.render(f5);
        body.render(f5);
        ass.render(f5);
        Right_L_wing.render(f5);
        Left_L_wing.render(f5);
        Right_M_wing.render(f5);
        Left_M_wing.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
        
        if(entity instanceof PoisonousLibelle) {
            PoisonousLibelle libelle = (PoisonousLibelle) entity;
            float wingRotation = libelle.getWingProgressScaled(0.2617994F);
            rotateWings(wings_left, -wingRotation);
            rotateWings(wings_right, wingRotation);
        }
    }
    
    private void rotateWings(List<ModelRenderer> wings, float rotation) {
        for(ModelRenderer wing : wings) {
            setRotation(wing, 0F, 0F, rotation);
        }
    }

}
