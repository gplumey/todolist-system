
import { test } from 'vitest'
import Todolists from './Todolists'
import { render } from '@testing-library/react'

test('Renders Todolists', () => {
    render(<Todolists />)
})