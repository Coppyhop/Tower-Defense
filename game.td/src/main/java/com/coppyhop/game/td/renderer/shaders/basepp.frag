#version 400 core

in vec2 passTexture;
in float passTime;

out vec4 outColor;

uniform sampler2D textureSampler;

void main(void){
	vec2 test = vec2(passTexture.x, passTexture.y *-1);
	outColor = texture(textureSampler, test);
}
