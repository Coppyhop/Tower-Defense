#version 130

in vec2 passTexture;
in vec3 color;

out vec4 outColor;

uniform sampler2D textureSampler;

void main(void){
	outColor = texture(textureSampler, passTexture) * vec4(color,1.0);
}
