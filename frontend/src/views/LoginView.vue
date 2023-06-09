<script setup>
import InputItem from '@/components/common/InputItem.vue'
import InputWrapper from '@/components/common/InputWrapper.vue'
import { ref, watch } from 'vue'
import ButtonItem from '@/components/common/ButtonItem.vue'
import ButtonWrapper from '@/components/common/ButtonWrapper.vue'
import * as authService from '@/services/auth.service'
import { useRouter } from 'vue-router'
import { useToast } from 'vue-toastification'
import 'vue-toastification/dist/index.css'

const router = useRouter()
const toast = useToast()

const login = ref('')
const loginError = ref('')
const password = ref('')
const passwordError = ref('')

const validateLogin = (value) => (!value ? 'Login is empty' : '')
const validatePassword = (value) => (!value ? 'Password is empty' : '')
const updateLoginError = (value) => (loginError.value = validateLogin(value))
const updatePasswordError = (value) => (passwordError.value = validatePassword(value))

const submit = () => {
  updateLoginError(login.value)
  updatePasswordError(password.value)

  if (!loginError.value && !passwordError.value) {
    authService
      .authenticate(login.value, password.value)
      .then(() => router.push('/chat'))
      .catch((error) => toast.error(error.response.data, { timeout: 3000 }))
  }
}

watch(login, (value) => updateLoginError(value))
watch(password, (value) => updatePasswordError(value))
</script>

<template>
  <form class="form" @submit.prevent="submit">
    <InputWrapper>
      <template #input>
        <InputItem id="login" v-model="login" :error="loginError" label="Login" name="login" />
      </template>
    </InputWrapper>
    <InputWrapper>
      <template #input>
        <InputItem
          id="password"
          v-model="password"
          :error="passwordError"
          label="Password"
          name="password"
          type="password"
        />
      </template>
    </InputWrapper>
    <ButtonWrapper>
      <template #button>
        <ButtonItem text="Login" type="submit" />
      </template>
    </ButtonWrapper>
    <router-link class="alternative-action" to="/registration"
      >Does not have an account?</router-link
    >
  </form>
</template>

<style scoped>
.form {
  display: flex;
  justify-content: center;
  flex-direction: column;
  width: 27.3rem;
  height: calc(100vh - 6.65rem);
  margin: -8rem auto 0 auto;
}
</style>