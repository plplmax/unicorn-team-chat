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
const confirmedPassword = ref('')
const confirmedPasswordError = ref('')

const validateLogin = (value) => {
  const trimmed = value.trim()
  if (!trimmed) {
    return 'Login is required'
  }

  const minInclusive = 4
  const maxInclusive = 12
  if (!(trimmed.length >= minInclusive && trimmed.length <= maxInclusive)) {
    return `Username should be from ${minInclusive} to ${maxInclusive} characters`
  }

  return ''
}
const validatePassword = (value) => {
  const trimmed = value.trim()
  if (!trimmed) {
    return 'Password is required'
  }

  const minInclusive = 6
  const maxInclusive = 8
  if (!(trimmed.length >= minInclusive && trimmed.length <= maxInclusive)) {
    return `Password should be from ${minInclusive} to ${maxInclusive} characters`
  }

  return ''
}
const validateConfirmedPassword = (value) =>
  value !== password.value ? 'Passwords do not match' : ''
const updateLoginError = (value) => (loginError.value = validateLogin(value))
const updatePasswordError = (value) => (passwordError.value = validatePassword(value))
const updateConfirmedPasswordError = (value) =>
  (confirmedPasswordError.value = validateConfirmedPassword(value))

const submit = () => {
  updateLoginError(login.value)
  updatePasswordError(password.value)
  updateConfirmedPasswordError(confirmedPassword.value)

  if (!loginError.value && !passwordError.value && !confirmedPasswordError.value) {
    authService
      .register(login.value, password.value, confirmedPassword.value)
      .then(() => {
          toast.success("Account have been created. Use your credentials to log in")
          router.push('/')
      })
      .catch((error) => toast.error(error.response.data, { timeout: 3000 }))
  }
}

watch(login, (value) => updateLoginError(value))
watch(password, (value) => updatePasswordError(value))
watch(confirmedPassword, (value) => updateConfirmedPasswordError(value))
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
    <InputWrapper>
      <template #input>
        <InputItem
          id="confirmed-password"
          v-model="confirmedPassword"
          :error="confirmedPasswordError"
          label="Confirm password"
          name="confirmed-password"
          type="password"
        />
      </template>
    </InputWrapper>
    <ButtonWrapper>
      <template #button>
        <ButtonItem text="Register" type="submit" />
      </template>
    </ButtonWrapper>
      <router-link class="alternative-action" to="/">Already have an account?</router-link>
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
