import React, { Children } from 'react'

interface props {
    children: React.ReactNode
}

const Header = (props: props) => {
    return (
        <>
            {props.children}
        </>

    )
}

export default Header