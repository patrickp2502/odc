import { Card } from '@mui/material'
import React from 'react'
import ResponsiveAppBar from '../../organisms/ResponsiveAppBar'
import PageTemplate from '../../templates/PageTemplate'

const Steps = () => {
    return (
        <PageTemplate header={<ResponsiveAppBar />}>
            <Card>Here comes the steps</Card>
        </PageTemplate>
    )
}

export default Steps