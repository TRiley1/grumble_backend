package com.thomas.grumble.models;

import lombok.Data;

@Data

public class AvatarConfig {
        private String avatarStyle;
        private String topType;
        private String accessoriesType;
        private String hairColor;
        private String facialHairType;
        private String facialHairColor;
        private String clotheType;
        private String clotheColor;
        private String eyeType;
        private String eyebrowType;
        private String mouthType;
        private String skinColor;

        public AvatarConfig() {
                this.avatarStyle = "Circle";
                this.topType = "ShortHairShortCurly";
                this.accessoriesType = "Kurt";
                this.hairColor = "Black";
                this.facialHairType = "Blank";
                this.facialHairColor = "Black";
                this.clotheType = "CollarSweater";
                this.clotheColor = "Red";
                this.eyeType = "Default";
                this.eyebrowType = "Default";
                this.mouthType = "Default";
                this.skinColor = "Light";
        }
}
