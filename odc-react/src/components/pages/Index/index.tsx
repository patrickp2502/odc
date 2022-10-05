import { Card } from '@mui/material'
import React from 'react'
import ResponsiveAppBar from '../../organisms/ResponsiveAppBar'
import PageTemplate from '../../templates/PageTemplate'

const Index: React.FC = () => {
    return (
        <PageTemplate header={<ResponsiveAppBar />}>
            <Card>This is the Index</Card>
        </PageTemplate>
    )
}

export default Index