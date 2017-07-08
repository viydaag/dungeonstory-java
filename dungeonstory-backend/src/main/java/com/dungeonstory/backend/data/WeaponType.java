package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

@Entity
@Table(name = "WeaponType")
public class WeaponType extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = -5780288141494436969L;

    public enum ProficiencyType {
        SIMPLE, MARTIAL
    }

    public enum SizeType {
        LIGHT, MEDIUM, HEAVY
    }

    public enum HandleType {
        ONE_HANDED, TWO_HANDED, VERSATILE
    }

    public enum UsageType {
        MELEE, RANGE, MELEE_RANGE
    }

    public enum RangeType {
        AMMUNITION, THROWN
    }

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Column(name = "proficiencyType", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProficiencyType proficiencyType;

    @NotNull
    @Column(name = "sizeType", nullable = false)
    @Enumerated(EnumType.STRING)
    private SizeType sizeType;

    @NotNull
    @Column(name = "handleType", nullable = false)
    @Enumerated(EnumType.STRING)
    private HandleType handleType;

    @NotNull
    @Column(name = "usageType", nullable = false)
    @Enumerated(EnumType.STRING)
    private UsageType usageType;

    @Column(name = "rangeType")
    @Enumerated(EnumType.STRING)
    private RangeType rangeType;

    @Pattern(regexp = "\\d+d\\d+([\\+\\-]\\d+)*")
    @Column(name = "oneHandBaseDamage")
    private String oneHandBaseDamage;

    @Pattern(regexp = "\\d+d\\d+([\\+\\-]\\d+)*")
    @Column(name = "twoHandBaseDamage")
    private String twoHandBaseDamage;

    @NotNull
    @ManyToOne
    @JoinFetch(JoinFetchType.INNER)
    @JoinColumn(name = "damageTypeId", nullable = false)
    private DamageType damageType;

    @Column(name = "isReach")
    private boolean isReach;

    @Column(name = "isFinesse")
    private boolean isFinesse;

    @Column(name = "isLoading")
    private boolean isLoading;

    @NotNull
    @Min(value = 0)
    @Column(name = "baseWeight")
    private double baseWeight;
    
    @NotNull
    @Min(value = 1)
    @Column(name = "basePrice", nullable = false)
    private int basePrice;

    //	@ElementCollection
    //	@CollectionTable(name = "WeaponProperties")
    //	@Column(name = "property")
    //	@Enumerated(EnumType.STRING)
    //	private Set<WeaponProperty> weaponProperties;

    public WeaponType() {
        super();
    }

    public WeaponType(String name, ProficiencyType proficiencyType, SizeType sizeType, HandleType handleType,
            UsageType usageType, DamageType damageType) {
        this();
        this.name = name;
        this.proficiencyType = proficiencyType;
        this.sizeType = sizeType;
        this.handleType = handleType;
        this.usageType = usageType;
        this.damageType = damageType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProficiencyType getProficiencyType() {
        return proficiencyType;
    }

    public void setProficiencyType(ProficiencyType proficiencyType) {
        this.proficiencyType = proficiencyType;
    }

    public SizeType getSizeType() {
        return sizeType;
    }

    public void setSizeType(SizeType sizeType) {
        this.sizeType = sizeType;
    }

    public HandleType getHandleType() {
        return handleType;
    }

    public void setHandleType(HandleType handleType) {
        this.handleType = handleType;
    }

    public UsageType getUsageType() {
        return usageType;
    }

    public void setUsageType(UsageType usageType) {
        this.usageType = usageType;
    }

    public RangeType getRangeType() {
        return rangeType;
    }

    public void setRangeType(RangeType rangeType) {
        this.rangeType = rangeType;
    }

    public String getOneHandBaseDamage() {
        return oneHandBaseDamage;
    }

    public void setOneHandBaseDamage(String baseDamage) {
        this.oneHandBaseDamage = baseDamage;
    }

    public String getTwoHandBaseDamage() {
        return twoHandBaseDamage;
    }

    public void setTwoHandBaseDamage(String twoHandBaseDamage) {
        this.twoHandBaseDamage = twoHandBaseDamage;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public boolean getIsReach() {
        return isReach;
    }

    public void setIsReach(boolean isReach) {
        this.isReach = isReach;
    }

    public boolean getIsFinesse() {
        return isFinesse;
    }

    public void setIsFinesse(boolean isFinesse) {
        this.isFinesse = isFinesse;
    }

    public boolean getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public double getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(double baseWeight) {
        this.baseWeight = baseWeight;
    }

    //	public Set<WeaponProperty> getWeaponProperties() {
    //        return weaponProperties;
    //    }
    //
    //    public void setWeaponProperties(Set<WeaponProperty> weaponProperties) {
    //        this.weaponProperties = weaponProperties;
    //    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return getName();
    }

}
