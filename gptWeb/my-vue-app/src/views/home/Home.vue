<script setup lang="ts">
import { Configuration, OpenAIApi } from "openai";
const configuration = new Configuration({
    organization: "org-hgfYdZ0UO3dVPxGtDJz5JtHo",
    apiKey: "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"
});
const openai = new OpenAIApi(configuration);

let response
try{
    response = await openai.createCompletion({
        model: aimode,
        prompt: questions,
        temperature: 0.5,
        max_tokens: 600,
        top_p: 1,
        frequency_penalty: 1,
        presence_penalty: 1.0,
        stop: ["ai"],
    });
    console.log(response)
}catch(e){
}


</script>

<template>
<div style="border: red 1px solid; width: 200px; height: 300px">

</div>
</template>

<style scoped>

</style>
